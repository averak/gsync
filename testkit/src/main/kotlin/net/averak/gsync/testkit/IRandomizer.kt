package net.averak.gsync.testkit

import org.jeasy.random.api.Randomizer

/**
 * 任意の型の各フィールドにランダム値を格納したインスタンスを生成するための生成ルールセット
 * ドメイン制約やDB制約に準拠したオブジェクトを生成したい場合に定義すること
 */
interface IRandomizer<T> : Randomizer<T> {

    override fun getRandomValue(): T

    fun getTypeToGenerate(): Class<T>
}
