package src.model;

import java.util.Stack;

/**
 * Gère les opérations d'annulation (undo) et de rétablissement (redo)
 * dans le jeu, en utilisant des piles pour suivre les commandes effectuées.
 */
public class UndoRedo {
    private Stack<Commande> undoStack = new Stack<>();
    private Stack<Commande> redoStack = new Stack<>();
    
    public void handle(Commande commande){
        undoStack.push(commande);
        redoStack.clear();
    }

    public void undo(){ 
        if(!undoStack.isEmpty()) {
            Commande commande = undoStack.pop();
            commande.annuler();
            redoStack.add(commande);
        }
    }

    /**
     * Rétablit la dernière commande annulée.
     * Si une commande existe dans la pile de rétablissement, elle est exécutée à nouveau
     * et déplacée dans la pile d'annulation.
     */
    public void redo(){
        if(!redoStack.isEmpty()) {
            Commande commande = redoStack.pop();
            commande.executer();
            undoStack.add(commande);
        }
    }
}