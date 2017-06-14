package com.example.admin.androidtp10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    TextView text_write;
    TextView line_one;
    TextView line_two;
    TextView line_three;
    TextView line_four;
    int resultat = 0;
    int chiffre =0;
    Stack pile = new Stack();
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_write = (TextView) findViewById(R.id.write_line);
        line_one = (TextView) findViewById(R.id.line_one);
        line_two = (TextView) findViewById(R.id.line_two);
        line_three = (TextView) findViewById(R.id.line_three);
        line_four = (TextView) findViewById(R.id.line_four);
    }

    //Fonction onclick sur les boutons
    public void bouton(View view) {
        int numero;
        Button bouton = (Button) view;
        numero = Integer.parseInt(bouton.getText().toString());
        ecrire_chiffre(numero);
    }

    public void enter(View view) {
        push_one();
    }

    public void plus(View view) {
        if (pile.size() == 0){
            push_one();
        }
        if(text_write.getText() != "" && pile.size()>0){
            resultat = ((Integer)pile.lastElement()) + (Integer.parseInt(text_write.getText().toString()));
            pile.pop();
            pile.push(resultat);
        }
        else{
            if(pile.size()>1){
                resultat = ((Integer)pile.lastElement()) + ((Integer)pile.elementAt(pile.size()-2));
                pile.pop();
                pile.pop();
                pile.push(resultat);
            }
        }
        refresh();
    }

    public void clear(View view) {
        pile.clear();
        refresh();
    }

    public void moins(View view) {
        if (pile.size() == 0){
            push_one();
        }
        if(text_write.getText() != "" && pile.size()>0){
            resultat = (Integer.parseInt(text_write.getText().toString())) - ((Integer)pile.lastElement());
            pile.pop();
            pile.push(resultat);
        }
        else{
            if(pile.size()>1){
                resultat = ((Integer)pile.elementAt(pile.size()-2)) - ((Integer)pile.lastElement());
                pile.pop();
                pile.pop();
                pile.push(resultat);
            }
        }
        refresh();
    }

    public void diviser(View view) {
        if (pile.size() == 0){
            push_one();
        }
        if(text_write.getText() != "" && pile.size()>0){
            try{
                resultat = (Integer.parseInt(text_write.getText().toString())) / ((Integer)pile.lastElement());
                pile.pop();
                pile.push(resultat);
            }
            catch(Exception e){
                toast = Toast.makeText(this, "Logic Error", Toast.LENGTH_LONG);
                toast.show();
            }
        }
        else{
            if(pile.size()>1){
                try{
                    resultat = ((Integer)pile.elementAt(pile.size()-2)) / ((Integer)pile.lastElement());
                    pile.pop();
                    pile.pop();
                    pile.push(resultat);
                }
                catch (Exception e){
                    toast = Toast.makeText(this, "Logic Error", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        }
        refresh();
    }

    public void pop(View view) {
        if(pile.size()>0){
            pile.pop();
        }
        refresh();
    }

    public void swap(View view) {
        Object temp1;
        Object temp2;
        if(text_write.getText() != ""){
            push_one();
        }
        if(pile.size()>1){
            temp1 = pile.lastElement();
            pile.pop();
            temp2 = pile.lastElement();
            pile.pop();
            pile.push(temp1);
            pile.push(temp2);

        }
        refresh();

    }

    public void ecrire_chiffre(int nombre){
        chiffre = Integer.parseInt(Integer.toString(chiffre) + Integer.toString(nombre));
        text_write.setText(Integer.toString(chiffre));
    }



    public void push_one(){
        pile.push(chiffre);
        refresh();
    }

    public void refresh(){
        line_one.setText("");
        line_two.setText("");
        line_three.setText("");
        line_four.setText("");
        if(pile.size()>0){
            line_one.setText(pile.lastElement().toString());
        }
        if(pile.size()> 1){
            line_two.setText(pile.elementAt(pile.size()-2).toString());
        }
        if(pile.size()> 2){
            line_three.setText(pile.elementAt(pile.size()-3).toString());
        }
        if(pile.size()> 3){
            line_four.setText(pile.elementAt(pile.size()-4).toString());
        }
        text_write.setText("");
        chiffre = 0;
    }
}