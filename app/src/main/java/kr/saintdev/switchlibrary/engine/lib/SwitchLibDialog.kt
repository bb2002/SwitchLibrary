package kr.saintdev.switchlibrary.engine.lib

import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity

object SwitchLibDialog {
    /**
     * @Date 08.23 2019
     * 닫을 수 없는 메세지 상자를 엽니다.
     */
    fun openNotificationDialog(title: String, message: String, context: AppCompatActivity) : AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setCancelable(false)
        return builder.show()
    }

    fun openNotificationDialog(title: Int, message: Int, context: AppCompatActivity) : AlertDialog {
        return openNotificationDialog(context.getString(title), context.getString(message), context)
    }

    /**
     * @Date 08.23 2019
     * 흔한 메세지 상자를 엽니다.
     */
    fun openMessageDialog(title: String, message: String, context: AppCompatActivity) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") {
                dialogInterface, _ -> dialogInterface.dismiss()
        }

        builder.show()
    }

    fun openMessageDialog(title: Int, message: Int, context: AppCompatActivity) =
        openMessageDialog(context.getString(title), context.getString(message), context)

    /**
     * @Date 08.23 2019
     * 흔한 질문 응답 상자를 엽니다.
     */
    fun openConfirmDialog(title: String, message: String, positiveListener: DialogInterface.OnClickListener, negativeListener: DialogInterface.OnClickListener, context: AppCompatActivity) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK", positiveListener)
        builder.setNegativeButton("Cancel", negativeListener)
        builder.show()
    }

    fun openConfirmDialog(title: Int, message: Int, positiveListener: DialogInterface.OnClickListener, negativeListener: DialogInterface.OnClickListener, context: AppCompatActivity) {
        openConfirmDialog(context.getString(title), context.getString(message), positiveListener, negativeListener, context)
    }
}