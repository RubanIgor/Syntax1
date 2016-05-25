package Parser;

import java.util.List;

public final class Main {
    public static void main(String[] args) {
//        final String inputString = "-(-(-(-1)))+2*3-4/(5)-6";
        final String inputString = "5 / (3 - 1)";
        final List<Lexem> lexems = new MakerLexemList(inputString).makeList();
        for (Lexem lexem : lexems) System.out.println(lexem);
        System.out.println("-------------------------------------");
        final TreeNode tree = new Parser(lexems).makeTree();
        TreeNode.printTreeNode(tree);
        System.out.println(" = " + TreeNode.calcTreeNode(tree));
    }    
}
