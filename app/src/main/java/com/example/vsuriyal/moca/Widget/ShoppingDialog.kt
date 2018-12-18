package com.example.vsuriyal.moca.Widget

import android.os.Bundle
import android.app.Dialog
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SwitchCompat
import android.view.View
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import com.example.vsuriyal.moca.Beans.BeanClass
import com.example.vsuriyal.moca.Fragment.BaseFragment
import com.example.vsuriyal.moca.R
import com.example.vsuriyal.moca.R.string.quantity
import com.example.vsuriyal.moca.Utils.DiscountEnum
import kotlinx.android.synthetic.main.shopping_dialog_view.shopping_dialog_cancel
import kotlinx.android.synthetic.main.shopping_dialog_view.shopping_dialog_discount_a
import kotlinx.android.synthetic.main.shopping_dialog_view.shopping_dialog_discount_b
import kotlinx.android.synthetic.main.shopping_dialog_view.shopping_dialog_discount_c
import kotlinx.android.synthetic.main.shopping_dialog_view.shopping_dialog_discount_d
import kotlinx.android.synthetic.main.shopping_dialog_view.shopping_dialog_minus
import kotlinx.android.synthetic.main.shopping_dialog_view.shopping_dialog_plus
import kotlinx.android.synthetic.main.shopping_dialog_view.shopping_dialog_quantity
import kotlinx.android.synthetic.main.shopping_dialog_view.shopping_dialog_save
import kotlinx.android.synthetic.main.shopping_dialog_view.shopping_dialog_title
import kotlinx.android.synthetic.main.shopping_dialog_view.view.*


class ShoppingDialog(val fragment: BaseFragment, val bean: BeanClass.ItemListBean) : Dialog(fragment.activity),
    android.view.View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.shopping_dialog_view)
    }

    override fun onStart() {
        super.onStart()
        updateQuantityToCurrentValue(1)
        shopping_dialog_title.text = bean.title
        shopping_dialog_plus.setOnClickListener(this)
        shopping_dialog_minus.setOnClickListener(this)
        shopping_dialog_save.setOnClickListener(this)
        shopping_dialog_cancel.setOnClickListener(this)
        shopping_dialog_discount_a.setOnClickListener(this)
        shopping_dialog_discount_b.setOnClickListener(this)
        shopping_dialog_discount_c.setOnClickListener(this)
        shopping_dialog_discount_d.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.getId()) {
            R.id.shopping_dialog_plus -> increaseQuantity()
            R.id.shopping_dialog_minus -> decreaseQuantity()
            R.id.shopping_dialog_save -> saveItem()
            R.id.shopping_dialog_cancel -> dismiss()
            R.id.shopping_dialog_discount_a -> viewSwitchOn(shopping_dialog_discount_a)
            R.id.shopping_dialog_discount_b -> viewSwitchOn(shopping_dialog_discount_b)
            R.id.shopping_dialog_discount_c -> viewSwitchOn(shopping_dialog_discount_c)
            R.id.shopping_dialog_discount_d -> viewSwitchOn(shopping_dialog_discount_d)
            else -> {
                dismiss()
            }
        }

    }

    private fun saveItemToKart() {
        fragment.addToShoppingKart(
            BeanClass.ShoppingListBean(
                bean.id,
                bean.title,
                bean.price,
                getQuantity(),
                getDiscounts()
            )
        )

    }

    private fun saveItem() {
        val quantity = getQuantity()
        if (quantity in 1..1000) {
            saveItemToKart()
            dismiss()
        } else {
            Toast.makeText(context, R.string.shopping_kart_quantity_rule, Toast.LENGTH_SHORT).show()
        }
    }

    private fun getDiscounts(): DiscountEnum {
        if (shopping_dialog_discount_a.isChecked())
            return DiscountEnum.DISCOUNT_A
        else if (shopping_dialog_discount_b.isChecked())
            return DiscountEnum.DISCOUNT_B
        else if (shopping_dialog_discount_c.isChecked())
            return DiscountEnum.DISCOUNT_C
        else if (shopping_dialog_discount_d.isChecked())
            return DiscountEnum.DISCOUNT_D
        return DiscountEnum.DISCOUNT_ZERO
    }

    private fun getQuantity(): Int {
        return Integer.parseInt(shopping_dialog_quantity.text.toString())
    }

    private fun increaseQuantity() {
        var quantity = getQuantity()
        if (quantity > 1000) {
            return
        }
        quantity++
        updateQuantityToCurrentValue(quantity)
    }

    private fun decreaseQuantity() {
        var quantity = getQuantity()
        if (quantity == 1) {
            return
        }
        quantity--
        updateQuantityToCurrentValue(quantity)
    }

    private fun updateQuantityToCurrentValue(quantity:Int) {
        shopping_dialog_quantity.setText(quantity.toString())
    }

    private fun viewSwitchOn(view: SwitchCompat) {
        switchOffAllView()
        view.isChecked = true
    }

    private fun switchOffAllView() {
        shopping_dialog_discount_a.isChecked = false
        shopping_dialog_discount_b.isChecked = false
        shopping_dialog_discount_c.isChecked = false
        shopping_dialog_discount_d.isChecked = false
    }
}
