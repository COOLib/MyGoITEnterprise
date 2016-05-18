package ua.goit;

public class Doubles {

    public Double parse(String s) {

        StateMachine sm = new StateMachine();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            sm.next(ch);
        }

        return sm.getResult();
    }

    private static class StateMachine {

        public void next(char c) {
            currentState = currentState.next(c, data);
        }

        State currentState = State.INIT;
        ParseData data = new ParseData();

        public Double getResult() {

            if (currentState == State.NUMBER) {
                return new Double(data.getNumber());
            } else if (currentState == State.DECIMAL) {
                return data.getDecimal();
            } else if (currentState == State.POINT_AFTER) {
                return new Double(data.getNumber());
            } else if (currentState == State.EXP_NUMBER) {
                return data.getDecimal();
            }
            return null;
        }

        public enum State {
            INIT {
                @Override
                public State next(char c, ParseData data) {

                    if (c - '0' >= 0 && c - '0' <= 9) {
                        data.addDigit(c - '0');

                        return NUMBER;
                    } else if (c == '+') {
                        data.addDigit(0);

                        return SIGN;
                    } else if (c == '-') {
                        data.addDigit(-1);

                        return SIGN;
                    } else if (c == '.') {
                        data.addDouble(0);

                        return POINT_BEFORE;
                    }
                    return INVALID_END;
                }
            }, NUMBER {
                @Override
                public State next(char c, ParseData data) {

                    if (c - '0' >= 0 && c - '0' <= 9) {
                        data.addDigit(c - '0');

                        return NUMBER;
                    } else if (c == '.') {
                        data.addDouble(0);

                        return POINT_AFTER;
                    }
                    return INVALID_END;
                }
            }, DECIMAL {
                @Override
                public State next(char c, ParseData data) {

                    if (c - '0' >= 0 && c - '0' <= 9) {
                        data.addDouble(c - '0');

                        return DECIMAL;
                    } else if (c == 'e') {
                        data.expDegree(-1);

                        return EXPONENT;
                    }
                    return INVALID_END;
                }
            }, SIGN {
                @Override
                public State next(char c, ParseData data) {

                    if (c - '0' >= 0 && c - '0' <= 9) {
                        data.addDigit(c - '0');

                        return NUMBER;
                    } else if (c == '.') {
                        data.addDouble(0);

                        return POINT_BEFORE;
                    }
                    return INVALID_END;
                }
            }, POINT_BEFORE {
                @Override
                public State next(char c, ParseData data) {

                    if (c - '0' >= 0 && c - '0' <= 9) {
                        data.addDouble(c - '0');

                        return DECIMAL;
                    }
                    return INVALID_END;
                }
            }, POINT_AFTER {
                @Override
                public State next(char c, ParseData data) {

                    if (c - '0' >= 0 && c - '0' <= 9) {
                        data.addDouble(c - '0');

                        return DECIMAL;
                    } else if (c == 'e') {

                        return EXPONENT;
                    }
                    return INVALID_END;
                }
            }, EXPONENT {
                @Override
                public State next(char c, ParseData data) {

                    if (c - '0' >= 0 && c - '0' <= 9) {
                        data.expDegree(c - '0');

                        return EXP_NUMBER;
                    } else if (c == '-') {
                        data.expDegree(0);

                        return EXP_SIGN;
                    }
                    return INVALID_END;
                }
            }, EXP_SIGN {
                @Override
                public State next(char c, ParseData data) {

                    if (c - '0' >= 0 && c - '0' <= 9) {
                        data.expDegree(c - '0');

                        return EXP_NUMBER;
                    }
                    return INVALID_END;
                }
            }, EXP_NUMBER {
                @Override
                public State next(char c, ParseData data) {

                    if (c - '0' >= 0 && c - '0' <= 9) {
                        data.expDegree(c - '0');

                        return EXP_NUMBER;
                    }
                    return INVALID_END;
                }
            }, VALID_END {
                @Override
                public State next(char c, ParseData data) {

                    if (c == ' ') {

                        return VALID_END;
                    }
                    return INVALID_END;
                }
            }, INVALID_END {
                @Override
                public State next(char c, ParseData data) {

                    return INVALID_END;
                }
            };

            public abstract State next(char c, ParseData data);
        }
    }

    public static class ParseData {

        private int number = 0;
        private int helpNumber = 0;
        private double decimal = 0.0;
        private int helpDecimal = 0;

        public int getNumber() {

            return number;
        }

        public double getDecimal() {

            return decimal;
        }

        public void addDigit(int i) {

            if (helpNumber < 0) {
                number = number * 10 - i;
            } else if (i == -1) {
                helpNumber = i;
            } else {
                number = number * 10 + i;
            }
        }

        public void addDouble(int i) {

            if (i == 0) {
                decimal = number;
            } else if (decimal < 0 && helpNumber < 0) {
                decimal = decimal - i / (1.0 * (Math.pow(10, ++helpDecimal)));
            } else if (decimal > 0 && helpNumber < 0) {
                decimal = (-1) * (decimal + i / (1.0 * (Math.pow(10, ++helpDecimal))));
            } else {
                decimal = decimal + i / (1.0 * (Math.pow(10, ++helpDecimal)));
            }
        }

        public void expDegree(int i) {

            if (i == -1) {
                helpNumber = i;
            } else if (helpNumber <= 0) {
                decimal = decimal / Math.pow(10, i);
            } else {
                decimal = decimal * Math.pow(10, i);
            }
        }
    }
}
