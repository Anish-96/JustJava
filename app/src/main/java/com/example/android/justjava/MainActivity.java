package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.util.*;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 1;
    private int whip = 1;
    private int choco = 2;
    private int cof = 5;
    private int Totalprice = 0;
    private String s = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if(quantity == 100)
        {
            Toast.makeText(this,"You cannot have more than 100 cups of coffees",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if(quantity == 1)
        {
            Toast.makeText(this,"You cannot have less than 1 cup of coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }
    /**
     * This method is called when the checkbox is clicked or not.
     */
    public String check_method(View v)
    {
        boolean isChecked = ((CheckBox) findViewById(R.id.checkbox1)).isChecked();
        boolean isChecked2 = ((CheckBox) findViewById(R.id.checkbox2)).isChecked();
        Totalprice = Totalprice + cof;
            if(isChecked == true || isChecked2 == true)
            {
                if(isChecked == true)
                {
                    Totalprice = Totalprice + whip;
                }
                if(isChecked2 == true)
                {
                    Totalprice = Totalprice + choco;
                }
            }
            return "Add Whipped Cream ? "+isChecked+"\nAdd Chocolate ? "+isChecked2;
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view)
    {
        EditText ed = (EditText) findViewById(R.id.edt);
        String s1 = ed.getText().toString();
        String s = check_method(view);
        int price = calculatePrice();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for "+s1);
        intent.putExtra(Intent.EXTRA_TEXT,"Name :"+s1+"\n"+s+"\nQuantity : "+quantity+"\nTotal : $"+price+"\nThank You !");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        //String priceDetails  = createOrderSummary(price, s, s1);
        Totalprice = 0;
       // displayMessage(priceDetails);
    }
    // This method will display the details of the customer..
//    public String createOrderSummary(int price, String str, String str1)
//    {
//        //return "Name :"+str1+"\n"+str+"\nQuantity : "+quantity+"\nTotal : $"+price+"\nThank You !";
//    }
    private void displayQuantity(int num)
    {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + num);
    }
    /**
     * Calculates the price of the order.
     *
     */
    private int calculatePrice() {
        int price = Totalprice * quantity;
        return price;
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView order_summary_text_view = (TextView) findViewById(R.id.order_summary_text_view);
        order_summary_text_view.setText(message);
    }

}