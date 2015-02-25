package designMode.Composite;

public class Tree {
	
	public TreeNode root=null;
	
	public Tree(String name){
		this.root=new TreeNode(name);
	}
	
	
	
	public static void main(String[] args){
		Tree tree=new Tree("animal");
		TreeNode node1=new TreeNode("dog");
		TreeNode node2=new TreeNode("cat");
		TreeNode sub_node1=new TreeNode("Siberian Husky");
		node1.add(sub_node1);
		tree.root.add(node1);
		tree.root.add(node2);
		System.out.println(tree.root.toString());
	}

}
