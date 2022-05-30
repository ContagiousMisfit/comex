package br.com.alura.comex;

import br.com.alura.comex.menu.MenuRelatorio;
import br.com.alura.comex.menu.MenuRelatorioGUI;

public class Main {


    public static void main(String[] args) throws Exception {

        MenuRelatorio menu = new MenuRelatorio();
        menu.executar();
        
        /*MenuRelatorioGUI menu2 = new MenuRelatorioGUI();
        menu2.executar();*/
    }
}
