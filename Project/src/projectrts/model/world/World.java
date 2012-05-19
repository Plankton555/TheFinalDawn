package projectrts.model.world;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.pebjorn.javautils.Math;

/**
 * The class containing the "world"
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public enum World implements IWorld {
	INSTANCE;

	/**
	 * Initializes the world.
	 */
	public void initializeWorld() {
		initNodes(height, width);
	}

	private Node[][] nodes;

	private int width = 100;
	private int height = 100;

	@Override
	public int getWorldWidth() {
		return width;
	}

	@Override
	public int getWorldHeight() {
		return height;
	}

	private void initNodes(int height, int width) {
		nodes = new Node[height][width];
		this.width = width;
		this.height = height;

		// Creates a matrix of nodes with the upper left at position (0.5, 0.5)
		// and the lower right at position (width+0.5, height+0.5)
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				nodes[i][j] = new Node(j + 0.5f, i + 0.5f);
			}
		}
		createNodeConnections();
		// Update all nodes distance to obstacles.
	}

	private void createNodeConnections() {
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes[i].length; j++) {
				// Add all nodes surrounding this node
				for (int k = i - 1; k <= i + 1; k++) {
					// If the row exists
					if (k >= 0 && k < nodes.length) {
						for (int l = j - 1; l <= j + 1; l++) {
							// If the column exists
							if (l >= 0 && l < nodes[i].length) {
								nodes[i][j].addNeighbour(nodes[k][l]);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public INode[][] getNodes() {
		Node[][] output = new Node[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				output[i][j] = nodes[i][j];
			}
		}
		return output;
	}

	@Override
	public INode getNodeAt(Position p) {
		int x = (int) p.getX();
		int y = (int) p.getY();
		x = (int) Math.clamp(x, 0, width - 1);
		y = (int) Math.clamp(y, 0, height - 1);
		return nodes[y][x];
	}

	@Override
	public void setNodesOccupied(INode nodeInCenter, float entitySize,
			int entityID) {
		List<INode> changingNodes = getNodesAt(nodeInCenter.getPosition(),
				entitySize);
		for (INode n : changingNodes) {
			n.setOccupied(entityID);
		}
	}

	@Override
	public List<INode> getNodesAt(Position centerPos, float size) {
		// Maybe find some other way to do this...
		List<INode> output = new ArrayList<INode>();
		int offset = (int) (size / 2);
		int centerX = (int) centerPos.getX();
		int centerY = (int) centerPos.getY();

		for (int i = centerY - offset; i <= centerY + offset; i++) {
			if (Math.isWithin(i, 0, width - 1)) {
				for (int j = centerX - offset; j <= centerX + offset; j++) {
					if (Math.isWithin(j, 0, height - 1)) {
						output.add(nodes[i][j]);
					}
				}
			}
		}
		return output;
	}
}