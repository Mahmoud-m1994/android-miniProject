/**
 * Created to pass params to another activities if needed
 */
package com.example.jetpcakcompose


sealed class Activity(val activityName: String) {
    object Main : Activity("main")
    object Secund : Activity("secund")

    fun getArg(vararg  args: String) : String {
        var output = buildString {
            append(activityName)
            args.forEach { item ->
                append("/$item")
            }
        }
        return output
    }

}