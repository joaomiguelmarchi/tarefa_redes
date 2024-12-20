# Aluno: Joao Miguel Steffen Marchi

1 Algoritmo de vetor de distância 

	O código implementa um algoritmo para calcular as menores distâncias entre os nós de uma rede.

	Inicialização:
		Cada nó conhece a distância para seus vizinhos diretos.

	Comunicação entre nós:
		Cada nó envia suas informações de distância para seus vizinhos.
		Os nós atualizam suas distâncias baseados nas informações recebidas, buscando sempre o caminho mais curto.
	Iteração:
		O processo continua até que não haja mais atualizações nas distâncias.
	Resultado:
		Ao final, cada nó terá a menor distância para todos os outros nós na rede.

2 Algoritmo de estado de enlace

	Este código implementa o algoritmo de Dijkstra para encontrar as menores distâncias de um nó inicial a todos os outros nós em um grafo.

	Definição do Grafo:
		Classes Graph e Edge: A classe Graph representa o grafo, e a classe Edge representa as arestas, que têm um nó de destino e um peso.

	Adição de Arestas:
		O método addEdge adiciona arestas ao grafo, conectando dois nós e atribuindo um peso a essa conexão.

	Algoritmo de Dijkstra:
		O método dijkstra calcula as menores distâncias a partir de um nó inicial (start).
		Utiliza uma fila de prioridade (PriorityQueue) para processar os nós na ordem da menor distância conhecida.
		Inicializa todas as distâncias como infinitas (Integer.MAX_VALUE), exceto a distância do nó inicial, que é 0.
		Enquanto a fila não estiver vazia, o nó com a menor distância é removido da fila e suas distâncias são atualizadas para os vizinhos, se um caminho mais curto for encontrado.

	Resultado:
		No método main, um exemplo de grafo é criado e arestas são adicionadas.
		O algoritmo de Dijkstra é executado a partir do nó 'A', e as menores distâncias de 'A' para todos os outros nós são impressas.

3 Algoritmo de vetor de caminhos

	Este algoritmo implementa a propagação de vetores de caminhos em uma rede de nós, calculando os caminhos mais curtos entre cada nó e todos os outros nós.

	Definição do Nó (NodePath):
		Cada nó tem um nome, uma lista de vizinhos com suas distâncias e um mapa de caminhos que armazena os caminhos conhecidos para outros nós.

	Recepção de Vetores de Caminho:
		O método receivePathVector atualiza os caminhos do nó com base nas informações recebidas de um vizinho. Se um caminho mais curto for encontrado, ele é atualizado.

	Envio de Vetores de Caminho:
		O método sendPathVector retorna uma cópia dos caminhos conhecidos do nó para serem enviados aos vizinhos.
		Simulação da Propagação de Vetores de Caminho (PathVectorSimulation):
		O método simulatePathVector itera sobre todos os nós e seus vizinhos, propagando e atualizando os vetores de caminho até que não haja mais atualizações.

	Resultado:
		No método main, um exemplo de rede é criado, e a simulação de vetores de caminho é executada.
		Os caminhos mais curtos de cada nó para todos os outros nós são impressos no final.
