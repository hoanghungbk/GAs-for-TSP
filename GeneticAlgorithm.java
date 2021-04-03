
import java.util.Iterator;

public class GeneticAlgorithm {
	//// mo ta:
	// Khoi tao quan the (population) ban dau
	// Danh gia quan the
	// Fitness: do phu hop
	// Chon loc
	// Tao ra the he tiep thep ( next generation): lai ghep, dot bien
	// Lap lai qua trinh
	// -------------------------------
	// initPopulation();
	// evalPopulation();
	// while(generation<maxGeneration) {
	// crossover();
	// mutate()
	// evalPopulation()
	// generation++;

	private int populationSize;
	private double mutationRate;
	private int elitismCount;
	private double crossoverRate;
	protected int tournamentSize;

	public GeneticAlgorithm(int populationSize, double mutationRate, double crossoveRate, int elitismCount,
			int tournamentSize) {
		this.populationSize = populationSize;
		this.mutationRate = mutationRate;
		this.crossoverRate = crossoveRate;
		this.tournamentSize = tournamentSize;
	}

	// tinh fitness
	public double calFitness(Individual individual, City cities[]) {
		Route route = new Route(individual, cities);
		double fitness = 1 / route.getDistance();
		individual.setFitness(fitness);
		return fitness;
	}

	// Khoi tao quan the
	public Population initPopulation(int chromosomeLength) {
		Population population = new Population(this.populationSize, chromosomeLength);
		return population;
	}

	// Danh gia quan the: tinh theo fitness
	public void evalPopulation(Population population, City cities[]) {
		double populationFitness = 0;// tong fitness cua moi individual
		for (Individual individual : population.getIndividual()) {
			populationFitness += this.calFitness(individual, cities);
		}
		double avgFitness = populationFitness / populationSize;
		population.setPopulationFitness(avgFitness);
	}

	// lựa chọn cha cho phép lai
	// Selects parent for crossover using tournament selection

	// * Tournament selection works by choosing N random individuals, and then
	// * choosing the best of those.
	public Individual selectParent(Population population) {
	}

	// Lai ghep
	public Population crossoverPopulation(Population population) {
		return newPopulation;
	}

	// Mutation
	public Population mutatePopulation(Population population) {
		// initialize new population
		Population newPopulation = new Population(this.populationSize);

		// Loop over current population by fitness
		for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
			Individual individual = population.getFittest(populationIndex);

			// skip mutation if this an elite individual
			if (populationIndex >= this.elitismCount) {
				// system.out.println("mutating population member"+populationIndex);
				// loop over individual's genes
				for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
					// system.out.println("/tGene index "+geneIndex);
					// does this gene need mutation
					if (this.mutationRate > Math.random()) {
						// get new gene position
						int newGenePos = (int) (Math.random() * individual.getChromosomeLength());
						// get genes to swap
						int gene1 = individual.getGene(newGenePos);
						int gene2 = individual.getGene(geneIndex);
						// swap genes
						individual.setGene(geneIndex, gene1);
						individual.setGene(newGenePos, gene2);

					}
				}
			}
			// add individual to population
			newPopulation.setIndividual(populationIndex, individual);
		}
		// return mutated population
		return newPopulation;

	}
}