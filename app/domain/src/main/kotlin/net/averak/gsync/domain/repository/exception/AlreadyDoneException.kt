package net.averak.gsync.domain.repository.exception

/**
 * 同じ冪等キーで既に操作が完了している場合に発生する例外
 *
 * この例外を usecase でキャッチしたら更新処理を直ちに停止し、最新の操作の状態を返すこと。
 * 「冪等に処理される」とは「同じレスポンスを返す」ではなく「更新が一度だけ行われる」という意味であるため、常に最新の状態を返せば良い。
 */
class AlreadyDoneException : RuntimeException("This operation has already done using the same idempotency key.")
