package edu.eci.cvds.calculadora;

import java.util.ArrayList;
import java.util.Random;

import javax.faces.bean.ApplicationScoped; 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.*;

@ManagedBean(name = "calculadoraBean")
@ApplicationScoped

@SessionScoped
public class Calculadora{

	static String cadenaEntrada = "";
	static double resultadoMean = 0;
	static double resultadoVariance = 0;
	static double resultadoStandardDeviation = 0;
	static double resultadoMode = 0;
	static double cantidadNumero = 0;


	public Calculadora(){
    	
    }
	
	private static double[] cadenaADouble() {
		String[] valores = cadenaEntrada.split(";");
		double[] resultadoValores = new double[valores.length];
		for (int i = 0; i<valores.length;i++) {
			resultadoValores[i]=Double.parseDouble(valores[i]);			
		}
		cantidadNumero = resultadoValores.length;
		return resultadoValores;
	}
	
	
	public static double calculateMean() {
		double[] m = cadenaADouble();
        double sum = 0;
        for (int i = 0; i < m.length; i++) {
            sum += m[i];
        }
        resultadoMean = sum / m.length;
        return resultadoMean ;
    }
    
    public static double calculateVariance(){
		double[] m = cadenaADouble();
        double sqDiff = 0;
        double n = m.length;
        for (int i = 0; i < n; i++) 
            sqDiff += (m[i] - calculateMean()) * (m[i] - calculateMean());
        resultadoVariance = sqDiff/n; 
        return resultadoVariance;
    }
    
    public static double calculateStandardDeviation(){
		double[] m = cadenaADouble();    	
        resultadoStandardDeviation = Math.sqrt(calculateVariance());
        return resultadoStandardDeviation;
    }
    
    public static double calculateMode() {
        double maxCount = 0;
        double resultadoMode=0;
        double m[] = cadenaADouble();
        double n = m.length;
        for (int i = 0; i < n; ++i) {
           double count = 0;
           for (int j = 0; j < n; ++j) {
              if (m[j] == m[i])
              ++count;
           }
           if (count > maxCount) {
              maxCount = count;
              resultadoMode = m[i];
           }
        }
        return resultadoMode;
     }
    
    public static void restart(){
    	cadenaEntrada = "";
    	resultadoMean = 0;
    	resultadoVariance = 0;
    	resultadoStandardDeviation = 0;
    	resultadoMode = 0;
    	cantidadNumero = 0;
    }
         
    
    public String getCadenaEntrada() {
		return cadenaEntrada;
	}

	public void setCadenaEntrada(String cadenaEntrada) {
		this.cadenaEntrada = cadenaEntrada;
	}

	public double getResultadoMean() {
		return resultadoMean;
	}

	public void setResultadoMean(double resultadoMean) {
		this.resultadoMean = resultadoMean;
	}

	public double getResultadoVariance() {
		return resultadoVariance;
	}

	public void setResultadoVariance(double resultadoVariance) {
		this.resultadoVariance = resultadoVariance;
	}

	public double getResultadoStandardDeviation() {
		return resultadoStandardDeviation;
	}

	public void setResultadoStandardDeviation(double resultadoStandardDeviation) {
		this.resultadoStandardDeviation = resultadoStandardDeviation;
	}

	public double getResultadoMode() {
		return resultadoMode;
	}

	public void setResultadoMode(double resultadoMode) {
		this.resultadoMode = resultadoMode;
	}

	public double getCantidadNumero() {
		return cantidadNumero;
	}

	public void setCantidadNumero(int cantidadNumero) {
		this.cantidadNumero = cantidadNumero;
	}
	
	@Override
	public String toString() {
		return "Calculadora [cadenaEntrada=" + cadenaEntrada + ", resultadoMean=" + resultadoMean
				+ ", resultadoVariance=" + resultadoVariance + ", resultadoStandartDeviation="
				+ resultadoStandardDeviation + ", resultadoMode=" + resultadoMode + ", cantidadNumero=" + cantidadNumero
				+ "]";
	}

}
    

