package ua.goit.actions;

import java.math.BigDecimal;

public interface Action {

    public BigDecimal unaryAction(BigDecimal argument1);

    public BigDecimal binaryAction(BigDecimal argument1, BigDecimal argument2);

    public boolean isUnary();
}
