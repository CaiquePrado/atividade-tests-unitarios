package com.example.atividade;

public class Calculadora {

  public Double soma(Double firstNumber, Double secondNumber) {
      return firstNumber + secondNumber;
  }

  public Double subtracao(Double firstNumber, Double secondNumber) {
      return firstNumber - secondNumber;
  }

  public Double multiplicacao(Double firstNumber, Double secondNumber) {
      return firstNumber * secondNumber;
  }

  public Double divisao(Double firstNumber, Double secondNumber) {
      if (secondNumber.equals(0D))
          throw new ArithmeticException("Impossible to divide by zero!");
      return firstNumber / secondNumber;
  }
}
