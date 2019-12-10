package com.example;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal {
	
	private SystemTray tray = SystemTray.getSystemTray();
	private PopupMenu popup = new PopupMenu();
	private TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("./icon.png"));
	private Menu menuCatalogos = new Menu("Catalogos");
	private Menu menuOperaciones = new Menu("Operaciones");
	private MenuItem itemUsuarios = new MenuItem("Usuarios");
	private MenuItem itemSucursales = new MenuItem("Sucursales");
	private MenuItem itemTarea1 = new MenuItem("Tarea 1");
	private MenuItem itemTarea2 = new MenuItem("Tarea 2");
	private MenuItem menuItemSalir = new MenuItem("Salir");

	public static void main(String[] args) {		
		new Principal();
	}
	
	/**
	 * Constructor
	 */
	public Principal() {
		if(SystemTray.isSupported()) { 
			initSysTray();
			addActionListeners();
		}	
	}
	
	private void initSysTray() {
		
		
		popup.add(menuCatalogos);
		popup.add(menuOperaciones);
		popup.addSeparator();
		popup.add(menuItemSalir);
		
		menuCatalogos.add(itemUsuarios);
		menuCatalogos.add(itemSucursales);
		menuOperaciones.add(itemTarea1);
		menuOperaciones.add(itemTarea2);
		
		trayIcon.setPopupMenu(popup);
		
		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.out.println("Error: " + e.getMessage());
		}
	
	}
	
	/**
	 * Configuramos los ActionListeners de cada componente
	 */
	private void addActionListeners() {
		
		menuItemSalir.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cerrando aplicacion...");
				System.exit(0);	// Cerramos la aplicacion			
			}
		});
		
		itemUsuarios.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				trayIcon.displayMessage("Informacion", "Abriendo catalogo de usuarios ...", MessageType.INFO);
				//JOptionPane.showMessageDialog(null, "Los datos estan incompletos.", "Error",JOptionPane.ERROR_MESSAGE);
			}
		});
		
		itemSucursales.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				trayIcon.displayMessage("Advertencia", "Abriendo catalogo de sucursales ...", MessageType.WARNING);
			}
		});
		
		itemTarea1.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				trayIcon.displayMessage("Error", "La tarea 1", MessageType.ERROR);
			}
		});
		
		itemTarea2.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				trayIcon.displayMessage("Info", "La tarea 2", MessageType.INFO);
			}
		});
	}
}
