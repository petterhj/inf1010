import java.util.Arrays;
import java.lang.Math;

class Tree {
	int nodes = 0;
	int level = 0;

	Tree() {
		int leafs = 6;

		// System.out.println((int)Math.pow(2, 3));

		generateTree(leafs);
		// System.out.println("=============================");
		// generateTree(3);


	}

	public void generateTree(int leafs) {
		int depth = this.calculateDepth(leafs);

		if (level == 0) {
			this.level = depth;
		}
		else
			level--;


		int rest = (leafs % 2);
		int parents = (leafs - 1);
		this.nodes = ((leafs * 2) - 1);

		System.out.println(" Blader:\t" + leafs);
		System.out.println(" Nodes:\t\t" + this.nodes);
		System.out.println(" Level:\t\t" + this.level);
		// System.out.println(" Threads:\t" + threads);
		// System.out.println(" Pr. th.:\t" + (leafs / threads));
		// System.out.println(" Rest:\t\t" + (leafs % threads));
		System.out.println("--------------------------");
		System.out.println(" Dybde:\t\t" + depth);
		System.out.println("--------------------------");
		System.out.println(" Foreldre:\t" + parents);
		System.out.println(" Rest:\t\t" + rest);
		// System.out.println(" Venstre:\t" + left);
		// System.out.println(" Høyre:\t\t" + right);



		while (depth > 0) {
			generateTree(parents);
			System.out.println(depth);
		}



	}

	public int calculateDepth(int leafs) {
		int nodes = leafs;
		int levels = 0;	
		
		//System.out.println(((nodes/2) + (nodes%2)));
		while (nodes != 1) {
			nodes = ((nodes/2) + (nodes%2));
			levels++;
		}

		return levels;
	}
}

class Test4 {
    public static void main(String[] args) {
        System.out.println();
        new Tree();
        System.out.println();
	}
}