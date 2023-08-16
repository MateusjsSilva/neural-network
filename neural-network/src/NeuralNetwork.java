import java.util.Arrays;

public class NeuralNetwork {

    // Função de ativação sigmoidal
    public static double sigmoid(double val) {
        return 1.0 / (1.0 + Math.exp(-val));
    }

    // Derivada da função sigmoidal
    public static double derivadaSigmoid(double val) {
        return val * (1 - val);
    }

    // Função para treinar a rede neural
    public static double[][] treinar(double taxaAprendizado, double[][] pesos, double[][] dadosEntrada,
            double[][] saidasEsperadas) {
        double[][] entrada = dadosEntrada;

        for (int i = 0; i < 10000; i++) {
            double erroQuadraticoMedio = 0;

            for (int j = 0; j < entrada.length; j++) {
                double[] saidaCamadaEntrada = new double[2];
                for (int k = 0; k < 2; k++) {
                    double soma = 0;
                    for (int l = 0; l < 2; l++) {
                        soma += entrada[j][l] * pesos[l][k];
                    }
                    saidaCamadaEntrada[k] = sigmoid(soma + pesos[2][k]);
                }

                double[] saidaCamadaSaida = new double[1];
                double soma = 0;
                for (int k = 0; k < 2; k++) {
                    soma += saidaCamadaEntrada[k] * pesos[k][2];
                }
                saidaCamadaSaida[0] = sigmoid(soma + pesos[2][2]);

                double erro = saidasEsperadas[j][0] - saidaCamadaSaida[0];

                double[] S = new double[3];
                for (int k = 2; k >= 0; k--) {
                    if (k == 2) {
                        S[k] = erro * derivadaSigmoid(saidaCamadaSaida[0]);
                    } else {
                        S[k] = (S[2] * pesos[k][2]) * derivadaSigmoid(saidaCamadaEntrada[k]);
                    }
                }

                for (int k = 0; k < 2; k++) {
                    pesos[k][2] += taxaAprendizado * saidaCamadaEntrada[k] * S[2];
                }
                pesos[2][2] += taxaAprendizado * S[2];

                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 2; l++) {
                        pesos[l][k] += taxaAprendizado * entrada[j][l] * S[k];
                    }
                    pesos[2][k] += taxaAprendizado * S[k];
                }

                erroQuadraticoMedio += Math.pow(erro, 2);
            }

            erroQuadraticoMedio /= entrada.length;
            System.out.println("------------------------- ÉPOCA [" + i + "] -------------------------\n");
            System.out.println("Pesos Atualizados: " + Arrays.deepToString(pesos));
            System.out.println("Erro Médio da época: " + erroQuadraticoMedio);
            System.out.println();

            if (Math.abs(erroQuadraticoMedio) < 0.0001) {
                return pesos;
            }
        }

        return pesos;
    }

    // Função para testar a rede neural com dados de teste
    public static void testar(double[][] pesos, double[][] dadosTeste) {
        double[][] entrada = dadosTeste;
        System.out.println("\n------------------------- RESULTADOS -------------------------\n");
        for (int j = 0; j < entrada.length; j++) {
            double[] saidaCamadaEntrada = new double[2];
            for (int k = 0; k < 2; k++) {
                double soma = 0;
                for (int l = 0; l < 2; l++) {
                    soma += entrada[j][l] * pesos[l][k];
                }
                saidaCamadaEntrada[k] = sigmoid(soma + pesos[2][k]);
            }

            double[] saidaCamadaSaida = new double[1];
            double soma = 0;
            for (int k = 0; k < 2; k++) {
                soma += saidaCamadaEntrada[k] * pesos[k][2];
            }
            saidaCamadaSaida[0] = sigmoid(soma + pesos[2][2]);

            System.out.println("Entrada: [" + Math.round(entrada[j][0]) + ", " + Math.round(entrada[j][1]) + "] Saída: "
                    + Arrays.toString(saidaCamadaSaida) + " Resultado: "
                    + (saidaCamadaSaida[0] < 0.5 ? "Futebol" : "Tênis"));
        }
        System.out.println();
    }

    public static void main(String[] args) {

        double taxaAprendizado = 0.5;

        double w11 = 0.2;
        double w12 = 0.4;

        double w21 = 0.2;
        double w22 = 0.3;

        double w31 = 0.4;
        double w32 = 0.5;

        double l1 = 0.8;
        double l2 = 0.2;
        double l3 = 0.1;

        // Definição dos pesos iniciais
        double[][] pesos = { { w11, w21, w31 }, { w12, w22, w32 }, { l1, l2, l3 } };

        // Dados de entrada para treinamento
        // Alcaraz(11), Messi(01), Djokovic (10), Neymar(00)
        double[][] dadosEntrada = { { 1, 1 }, { 0, 1 }, { 1, 0 }, { 0, 0 }, { 1, 1 } };

        // Saídas esperadas para os dados de entrada
        double[][] saidasEsperadas = { { 0 }, { 1 }, { 1 }, { 0 }, { 0 } };

        // // Saídas esperadas para os dados de entrada
        // double[][] saidasEsperadas = { { 1 }, { 0 }, { 1 }, { 0 }, { 1 } };

        // Treinamento da rede neural
        double[][] pesosTreinados = treinar(taxaAprendizado, pesos, dadosEntrada, saidasEsperadas);

        // Dados de teste para avaliar a rede neural treinada
        double[][] dadosTeste = {
                { 0, 0 },
                { 0, 1 },
                { 1, 0 },
                { 1, 1 },
        };

        for (int index = 0; index < pesosTreinados.length; index++) {
            for (int index1 = 0; index1 < pesosTreinados.length; index1++) {
                System.out.println(pesosTreinados[index][index1]);
            }
        }

        // Teste da rede neural treinada
        testar(pesosTreinados, dadosTeste);
    }
}
