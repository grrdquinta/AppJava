package Vista;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import Controlador.ControllerPaquete; 

public class TableActionCellEditor extends AbstractCellEditor implements TableCellEditor {

    private PanelAction action;
    private ControllerPaquete controller;
    private int currentRow;

    public TableActionCellEditor(ControllerPaquete controller) {
        this.controller = controller;
        action = new PanelAction();

        action.getBtnEditar().addActionListener(e -> {
            controller.handleEditAction(currentRow);
        });

        action.getBtnEliminar().addActionListener(e -> {
            controller.handleDeleteAction(currentRow);
        });
    }

    @Override
    public Object getCellEditorValue() {
        return null;  
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        currentRow = row;
        return action;
    }
}
