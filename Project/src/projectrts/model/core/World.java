package projectrts.model.core;

public class World {
	private ITile[][] tileMap;
	private Node[][] nodes;
	
	public World(int height, int width) {
		initializeTileMap(height, width);
		initNodes(height, width);
	}
	
	private void initializeTileMap(int height, int width) {
		tileMap = new ITile[height][width];
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				tileMap[i][j] = new Tile();
			}
		}
	}
	
	private void initNodes(int height, int width)
	{
		nodes = new Node[height+1][width+1];
		
		// Creates a matrix of nodes with the upper left at position (0,0)
		// and the lower right at position (width,height)
		for (int i = 0; i <= height; i++)
		{
			for (int j = 0; j <= width; j++)
			{
				nodes[i][j] = new Node(j, i);
			}
		}
		createNodeConnections();
	}
	
	private void createNodeConnections()
	{
		for (int i=0; i<nodes.length; i++)
		{
			for (int j=0; j<nodes[i].length; j++)
			{
				// Add all nodes surrounding this node
				for (int k=i-1; k<=i+1; k++)
				{
					// If the row exists
					if (k >= 0 && k < nodes.length)
					{
						for (int l=j-1; l<=j+1; l++)
						{
							// If the column exists
							if (l >= 0 && l < nodes[i].length)
							{
								nodes[i][j].addNeighbour(nodes[k][l]);
							}
						}
					}
				}
			}
		}
	}
	
	public Node[][] getNodes()
	{
		return nodes;
	}

	public ITile[][] getTileMap()
	{
		return tileMap;
	}
	
	/*
	public static void main(String[] args)
	{
		World world = new World(10, 10);
		Node[][] myNodes = world.getNodes();
		
		int x = 5;
		int y = 5;
		System.out.println(myNodes[y][x].getPosition());
		System.out.println("has the neighbours:");
		
		for (Node n : myNodes[y][x].getNeighbours())
		{
			System.out.println(n.getPosition());
		}
	}
	*/
}
