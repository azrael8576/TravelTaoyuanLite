package com.wei.traveltaoyuanlite.core.result

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retryWhen
import timber.log.Timber

/**
 * DataSourceResult 是一個封裝數據源結果的密封接口，它可能是成功(Success)、錯誤(Error)或正在加載(Loading)的狀態。
 * @param T 數據源結果的型別參數。
 */
sealed interface DataSourceResult<out T> {
    data class Success<T>(val data: T) : DataSourceResult<T>

    data class Error(val exception: Throwable? = null) : DataSourceResult<Nothing>

    data object Loading : DataSourceResult<Nothing>
}

/**
 * 此擴展函數將一個 Flow<T> 轉換為一個 Flow<DataSourceResult<T>>，將流中的每個元素封裝為 DataSourceResult.Success，
 * 在流開始時發出 DataSourceResult.Loading，並在流出現錯誤時發出 DataSourceResult.Error。
 *
 * @return 一個新的 Flow<DataSourceResult<T>>，其元素是 DataSourceResult。
 */
fun <T> Flow<T>.asDataSourceResult(): Flow<DataSourceResult<T>> {
    return this
        .map<T, DataSourceResult<T>> {
            DataSourceResult.Success(it)
        }
        .onStart { emit(DataSourceResult.Loading) }
        .catch { emit(DataSourceResult.Error(it)) }
}

/**
 * 與 asDataSourceResult 相似，但在遇到錯誤時可自動重試。
 *
 * @param maxRetries 最大重試次數，預設為 3。
 * @param traceTag 日誌標籤，便於在重試或錯誤時紀錄。
 *
 * @return Flow<DataSourceResult<T>>
 */
fun <T> Flow<T>.asDataSourceResultWithRetry(
    maxRetries: Long = 3,
    traceTag: String,
): Flow<DataSourceResult<T>> {
    return this
        .map<T, DataSourceResult<T>> {
            DataSourceResult.Success(it)
        }
        .onStart {
            emit(DataSourceResult.Loading)
        }
        .retryWhen { _, attempt ->
            // cause 是拋出的異常, attempt 是第幾次重試(從0開始)
            if (attempt < maxRetries) {
                Timber.e("$traceTag Retrying API request due to timeout... (attempt ${attempt + 1})")
                delay(1000L * (attempt + 1)) // 指數回退或固定延遲
                true // 返回 true 代表要繼續重試
            } else {
                false // 返回 false 不再重試, 交給後續 catch
            }
        }
        .catch { e ->
            emit(DataSourceResult.Error(e))
        }
}
