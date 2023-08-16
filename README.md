**Neural Network**

Este repositório contém uma implementação em Java de uma rede neural simples para classificação binária. A arquitetura da rede neural consiste em três camadas: uma camada de entrada, uma camada oculta e uma camada de saída.

### Conteúdo

- `NeuralNetwork.java`: A implementação principal da rede neural, incluindo funções de ativação, função de treinamento e função de teste.
- `README.md`: Este arquivo de documentação, que fornece uma visão geral do código e seu uso.

### Implementação da Rede Neural

A classe `NeuralNetwork` contém as seguintes funções-chave:

1. `sigmoid(double val)`: Função de ativação que retorna o valor sigmoid do valor de entrada.
2. `derivadaSigmoid(double val)`: Derivada da função de ativação sigmoidal.
3. `treinar(double taxaAprendizado, double[][] pesos, double[][] dadosEntrada, double[][] saidasEsperadas)`: Função para treinar a rede neural. Ela utiliza a retropropagação para ajustar os pesos com base nos dados de entrada e nas saídas esperadas.
4. `testar(double[][] pesos, double[][] dadosTeste)`: Função para testar a rede neural treinada usando dados de teste.

### Como Usar

1. Compile e execute o arquivo `NeuralNetwork.java` utilizando um compilador Java ou um ambiente de desenvolvimento integrado (IDE) que suporte Java.

2. A função `main` no código fornece um exemplo de como usar a rede neural. Ela inicializa pesos, dados de entrada e saídas esperadas. A rede é treinada usando os dados de treinamento fornecidos e, em seguida, testada nos dados de teste.

3. O processo de treinamento imprime os pesos atualizados e o erro médio quadrático para cada época.

4. O processo de teste imprime as previsões da rede para os dados de teste.

### Ajustando Parâmetros

Você pode modificar os parâmetros e variáveis no código para experimentar com diferentes configurações da rede neural, como alterar a taxa de aprendizado, o número de épocas e os pesos iniciais.

### Requisitos

- Kit de Desenvolvimento Java (JDK)

### Aviso

Este código é destinado a fins educacionais e demonstra uma implementação básica de rede neural. Para tarefas mais complexas, considere o uso de bibliotecas e frameworks de aprendizado profundo estabelecidos.