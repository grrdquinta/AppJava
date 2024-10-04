/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import javax.swing.JButton;

/**
 *
 * @author gerst
 */
public class PaginationItemRenderStyle extends DefaultPaginationItemRender {

    @Override
    public JButton createButton(Object value, boolean isPrevious, boolean isNext, boolean enable) {
        JButton button = super.createButton(value, isPrevious, isNext, enable);
        button.setUI(new ButtonUI());
        return button;
    }

    @Override
    public Object createPreviousIcon() {
        return "Previous";
    }

    @Override
    public Object createNextIcon() {
        return "Next";
    }
}
