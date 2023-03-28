
package com.mycompany.faturamentodiariomaven;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {


    public static void main(String[] args) throws IOException {
        
     ObjectMapper mapper = new ObjectMapper();
        FaturamentoMensalDTO faturamentoMensalDTO = mapper.readValue(new File("faturamento_mensal.json"), FaturamentoMensalDTO.class);
        double[] faturamentoDiario = faturamentoMensalDTO.getFaturamentoDiario();
        double mediaMensal = calcularMediaMensal(faturamentoDiario);
        System.out.println("Menor faturamento diário: " + calcularMenorFaturamentoDiario(faturamentoDiario));
        System.out.println("Maior faturamento diário: " + calcularMaiorFaturamentoDiario(faturamentoDiario));
        System.out.println("Número de dias acima da média mensal: " + calcularDiasAcimaDaMediaMensal(faturamentoDiario, mediaMensal));
    }

    public static double calcularMediaMensal(double[] faturamentoDiario) {
        double soma = 0.0;
        int count = 0;
        for (double valor : faturamentoDiario) {
            if (valor > 0) { // considerar apenas dias com faturamento
                soma += valor;
                count++;
            }
        }
        return soma / count;
    }

    public static double calcularMenorFaturamentoDiario(double[] faturamentoDiario) {
        double menorFaturamento = Double.MAX_VALUE;
        for (double valor : faturamentoDiario) {
            if (valor > 0 && valor < menorFaturamento) { // considerar apenas dias com faturamento
                menorFaturamento = valor;
            }
        }
        return menorFaturamento;
    }

    public static double calcularMaiorFaturamentoDiario(double[] faturamentoDiario) {
        double maiorFaturamento = Double.MIN_VALUE;
        for (double valor : faturamentoDiario) {
            if (valor > maiorFaturamento) {
                maiorFaturamento = valor;
            }
        }
        return maiorFaturamento;
    }

    public static int calcularDiasAcimaDaMediaMensal(double[] faturamentoDiario, double mediaMensal) {
        int count = 0;
        for (double valor : faturamentoDiario) {
            if (valor > mediaMensal) {
                count++;
            }
        }
        return count;

    }

    public static class FaturamentoMensalDTO {

        private double[] faturamentoDiario;

        public double[] getFaturamentoDiario() {
            return faturamentoDiario;
        }

        public void setFaturamentoDiario(double[] faturamentoDiario) {
            this.faturamentoDiario = faturamentoDiario;
        }
    }
    
}
