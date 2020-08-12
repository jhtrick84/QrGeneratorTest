package kr.co.yjh.qrgentest

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import net.glxn.qrgen.android.QRCode
import java.util.*


class ScrollingActivity : AppCompatActivity() {

    private val qrSize: Int by lazy {
        dpToPx(100.0f)
    }

    private val text by lazy {
        arrayListOf(
            "가나다라마바사아자차카타파하",
            "가나다라마바사아",
            "ABCDEFGHIJ",
            "abcdefghijklmnopqrstuvwxyz",
            "가나다라마바사아 ABCDEFGH 2131123",
            "!@$$#@%ㅛ%$@^$#$%@#$^!*&*^*",
            "-",
            "가나다 ABC abc 123 %@@"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        initZxing()
        initQrGen()
        initText()
    }

    private fun initText() {
        text.run {
            findViewById<AppCompatTextView>(R.id.tvQr1).text = get(0)
            findViewById<AppCompatTextView>(R.id.tvQr2).text = get(1)
            findViewById<AppCompatTextView>(R.id.tvQr3).text = get(2)
            findViewById<AppCompatTextView>(R.id.tvQr4).text = get(3)
            findViewById<AppCompatTextView>(R.id.tvQr5).text = get(4)
            findViewById<AppCompatTextView>(R.id.tvQr6).text = get(5)
            findViewById<AppCompatTextView>(R.id.tvQr7).text = get(6)
        }
    }

    private fun initZxing() {
        text.run {
            Log.e("YJHTEST", "ZXING START")
            findViewById<AppCompatImageView>(R.id.ivQr1).setImageBitmap(
                encodeAsBitmap(get(0))
            )

            findViewById<AppCompatImageView>(R.id.ivQr2).setImageBitmap(
                encodeAsBitmap(get(1))
            )

            findViewById<AppCompatImageView>(R.id.ivQr3).setImageBitmap(
                encodeAsBitmap(get(2))
            )

            findViewById<AppCompatImageView>(R.id.ivQr4).setImageBitmap(
                encodeAsBitmap(get(3))
            )

            findViewById<AppCompatImageView>(R.id.ivQr5).setImageBitmap(
                encodeAsBitmap(get(4))
            )

            findViewById<AppCompatImageView>(R.id.ivQr6).setImageBitmap(
                encodeAsBitmap(get(5))
            )

            findViewById<AppCompatImageView>(R.id.ivQr7).setImageBitmap(
                encodeAsBitmap(get(6))
            )
            Log.e("YJHTEST", "ZXING END")
        }
    }

    private fun initQrGen() {
        text.run {
            Log.e("YJHTEST", "QRGEN START")
            findViewById<AppCompatImageView>(R.id.ivQrGen1).setImageBitmap(
                QRCode.from(get(0))
                    .withCharset("UTF-8")
                    .withHint(EncodeHintType.MARGIN, 1)
                    .withSize(qrSize, qrSize)
                    .bitmap()
            )

            findViewById<AppCompatImageView>(R.id.ivQrGen2).setImageBitmap(
                QRCode.from(get(1))
                    .withCharset("UTF-8")
                    .withHint(EncodeHintType.MARGIN, 1)
                    .withSize(qrSize, qrSize)
                    .bitmap()
            )

            findViewById<AppCompatImageView>(R.id.ivQrGen3).setImageBitmap(
                QRCode.from(get(2))
                    .withCharset("UTF-8")
                    .withHint(EncodeHintType.MARGIN, 1)
                    .withSize(qrSize, qrSize)
                    .bitmap()
            )

            findViewById<AppCompatImageView>(R.id.ivQrGen4).setImageBitmap(
                QRCode.from(get(3))
                    .withCharset("UTF-8")
                    .withHint(EncodeHintType.MARGIN, 1)
                    .withSize(qrSize, qrSize)
                    .bitmap()
            )

            findViewById<AppCompatImageView>(R.id.ivQrGen5).setImageBitmap(
                QRCode.from(get(4))
                    .withCharset("UTF-8")
                    .withHint(EncodeHintType.MARGIN, 1)
                    .withSize(qrSize, qrSize)
                    .bitmap()
            )

            findViewById<AppCompatImageView>(R.id.ivQrGen6).setImageBitmap(
                QRCode.from(get(5))
                    .withCharset("UTF-8")
                    .withHint(EncodeHintType.MARGIN, 1)
                    .withSize(qrSize, qrSize)
                    .bitmap()
            )

            findViewById<AppCompatImageView>(R.id.ivQrGen7).setImageBitmap(
                QRCode.from(get(6))
                    .withCharset("UTF-8")
                    .withHint(EncodeHintType.MARGIN, 1)
                    .withSize(qrSize, qrSize)
                    .bitmap()
            )
            Log.e("YJHTEST", "QRGEN END")
        }
    }

    private fun dpToPx(dp: Float): Int {
        return (dp * (resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }

    private fun getBitmapFromQRGen(text: String): Bitmap? {
        return QRCode.from(text)
            .withCharset("UTF-8")
            .withHint(EncodeHintType.MARGIN, 1)
            .withSize(qrSize, qrSize)
            .bitmap()
    }

    //    @Throws(WriterException::class)
    fun encodeAsBitmap(text: String): Bitmap? {
        val result: BitMatrix
        result = try {
            val hints = Hashtable<EncodeHintType, Any>().apply {
                put(EncodeHintType.CHARACTER_SET, "UTF-8")
                put(EncodeHintType.MARGIN, 0)
            }
            MultiFormatWriter().encode(
                text,
                BarcodeFormat.QR_CODE, qrSize, qrSize, hints
            )
        } catch (iae: IllegalArgumentException) {
            // Unsupported format
            return null
        }
        val w = result.width
        val h = result.height
        val pixels = IntArray(w * h)
        for (y in 0 until h) {
            val offset = y * w
            for (x in 0 until w) {
                pixels[offset + x] = if (result[x, y]) Color.BLACK else Color.WHITE
            }
        }
        val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, qrSize, 0, 0, w, h)
        return bitmap
    }
}