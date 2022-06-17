package com.example.application.views.component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class NumericField extends TextField {
    public static final int DEFAULT_VALUE = 0;
    public static final int DEFAULT_INCREMENT = 1;

    private int numericVal;
    private int incrementVal;
    private int decrementVal;

    private Button subBtn;
    private Button addBtn;

    public NumericField() {
        this(DEFAULT_VALUE, DEFAULT_INCREMENT, -DEFAULT_INCREMENT);
    }
    public NumericField(int value, int increment, int decrement) {
        setNumericVal(value);//numericVal = value;
        incrementVal = increment;
        decrementVal = decrement;

        setPattern("-?[0-9]*");
        setPreventInvalidInput(true);

        addChangeListener(e -> {
            String text = e.getSource().getValue();
            if (StringUtils.isNumeric(text)) setNumericVal(Integer.parseInt(text));//numericVal = Integer.valueOf(text);
            else setNumericVal(DEFAULT_VALUE);//numericVal = DEFAULT_VALUE;
        });

        subBtn = new Button("-", e -> {setNumericVal(numericVal+decrementVal);});//numericVal += decrementVal;
        addBtn = new Button("+", e -> {setNumericVal(numericVal+incrementVal);});//numericVal += incrementVal;

        getElement().setAttribute("theme", "numeric");
        stylesBtn();

        addToPrefix(subBtn);
        addToSuffix(addBtn);
    }

    public void stylesBtn() {
        subBtn.getElement().setAttribute("theme", "icon");
        addBtn.getElement().setAttribute("theme", "icon");
    }

    public void setNumericVal(int value) {
        numericVal = value;
        setValue(String.valueOf(value));
    }
}
