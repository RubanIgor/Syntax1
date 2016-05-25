package Parser;

public class TreeNode {
    private final Lexem lexem;
    private final TreeNode leftChild, rightChild;

    public TreeNode(Lexem lexem, TreeNode leftChild, TreeNode rightChild) {
        this.lexem = lexem;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
    
    public TreeNode(Lexem lexem) {
        this(lexem, null, null);
    }
    
    public static void printTreeNode(TreeNode root) {
        if (root == null) return;
        System.out.print("(");
        printTreeNode(root.leftChild);
        System.out.print(root.lexem);
        printTreeNode(root.rightChild);
        System.out.print(")");
    }

    public static double calcTreeNode(TreeNode root) {
        if (root == null) throw new RuntimeException("Ошибка запуска: <calcTreeNode(null)>");
        LexemType curLexemType = root.lexem.getLexemType();
        if (curLexemType == LexemType.Number) return Double.parseDouble(root.lexem.getLexemValue());
        double leftValue  = calcTreeNode(root.leftChild);
        double rightValue = calcTreeNode(root.rightChild);
        switch (curLexemType) {
            case Plus               : return leftValue + rightValue;
            case Minus              : return leftValue - rightValue;
            case Mult               : return leftValue * rightValue;
            case Div                : {
                                        if (rightValue == 0.0) throw new RuntimeException("Деление на 0");
                                        return leftValue / rightValue;
                                      }
            default                 : throw new RuntimeException("Неивестный узел: <" + root.lexem.getLexemType() + ">");
        }
    }
    
}
