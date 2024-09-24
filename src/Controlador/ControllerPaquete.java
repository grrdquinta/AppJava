package Controlador;
    import Modelo.mdlPaquetes;
import Vista.PanelAction;
import Vista.PaquetesPanel;
import Vista.TableActionCellEditor;
import Vista.TableActionCellRender;
import Vista.panelActualizarPaquete;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ControllerPaquete implements MouseListener {
    private PaquetesPanel vista;
    private mdlPaquetes modelo;
    public PanelAction botonAction;
    
    public ControllerPaquete(mdlPaquetes modelo, PaquetesPanel vista) {
        this.vista = vista;
        this.modelo = modelo;
        this.botonAction = new PanelAction();
        
        mostrarPaquetes(vista.jtbPaquetes);
        
        vista.jtbPaquetes.addMouseListener(this);
        botonAction.btnEditar.addMouseListener(this);
        botonAction.btnEliminar.addMouseListener(this);
    }
    
    public void mostrarPaquetes(JTable tabla) {
        modelo.obtenerPaquetes();
        
        DefaultTableModel modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == getColumnCount() - 1; 
            }
        };
        
        modeloTabla.setColumnIdentifiers(new Object[]{
            "ID Paquete", "Cliente", "Fecha Inicio", "Dirección", "Destino", "Peso", "Alto", "Largo", "Ancho", "Acción"
        });
        
        List<Object[]> paquetes = modelo.getPaquetesData();
        
        for (Object[] paquete : paquetes) {
            modeloTabla.addRow(paquete);
        }
        
        tabla.setModel(modeloTabla);
        
        tabla.getColumnModel().getColumn(5).setMinWidth(0);
        tabla.getColumnModel().getColumn(5).setMaxWidth(0);
        tabla.getColumnModel().getColumn(5).setWidth(0);
        
        tabla.getColumnModel().getColumn(6).setMinWidth(0);
        tabla.getColumnModel().getColumn(6).setMaxWidth(0);
        tabla.getColumnModel().getColumn(6).setWidth(0);
        
        tabla.getColumnModel().getColumn(7).setMinWidth(0);
        tabla.getColumnModel().getColumn(7).setMaxWidth(0);
        tabla.getColumnModel().getColumn(7).setWidth(0);
        
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setMaxWidth(0);
        tabla.getColumnModel().getColumn(8).setWidth(0);
        
        int lastColumnIndex = tabla.getColumnCount() - 1;
        tabla.getColumnModel().getColumn(lastColumnIndex).setCellRenderer(new TableActionCellRender());
        tabla.getColumnModel().getColumn(lastColumnIndex).setCellEditor(new TableActionCellEditor(this));
    }
    
    public void handleEditAction(int row) {
        modelo.almacenarDatosTabla(vista.jtbPaquetes);
        
        panelActualizarPaquete paq = new panelActualizarPaquete();
        
        ControllerInfoPaquete controllerInfo = new ControllerInfoPaquete(paq, modelo);
        
        controllerInfo.llenarCampos();
        
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(vista), "Actualizar Paquete", true);
        dialog.setContentPane(paq);
        dialog.pack();
        dialog.setLocationRelativeTo(vista);
        dialog.setVisible(true);
    }

    public void handleDeleteAction(int row) {
        
        DefaultTableModel modeloTabla = (DefaultTableModel) vista.jtbPaquetes.getModel();
        
        Object value = modeloTabla.getValueAt(row, 0);
        if (value != null) {
            try {
                int idPaquete = Integer.parseInt(value.toString());

                int confirm = JOptionPane.showConfirmDialog(vista, "¿Estás seguro de que deseas eliminar este paquete?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    modelo.eliminarPaquete(idPaquete);
                    mostrarPaquetes(vista.jtbPaquetes);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(vista, "Error al convertir el ID del paquete: " + e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(vista, "Error al eliminar el paquete: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(vista, "El ID del paquete no es válido.");
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
