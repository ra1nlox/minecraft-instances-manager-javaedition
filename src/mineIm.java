import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.ini4j.*;

public class mineIm implements ActionListener {

	// fonts and base variables
	private static JFrame frame;
	private static JPanel panel;
	static Font defaultfont = new Font("MS Shell Dlg 2", Font.PLAIN, 11);
	static Font defaultBoldfont = new Font("MS Shell Dlg 2", Font.BOLD, 12);
	static Font defaultSmallfont = new Font("MS Shell Dlg 2", Font.BOLD, 9);

	// detecting OS variables
	private static String OS = System.getProperty("os.name").toLowerCase();
	private static boolean IS_WINDOWS = (OS.indexOf("win") >= 0);
	private static boolean IS_MAC = (OS.indexOf("mac") >= 0);
	private static boolean IS_UNIX = (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);

	// variables
	private static String userdir;
	private static String minecraft_directory;
	private static String minecraft_instance_manager_directory;
	private static String instances_directory;
	private static String config_file_string;
	private static File config_file;

	// config variable
	private static Wini config;

	// labels
	private static JLabel activeInstance_label;
	private static JLabel version;
	private static JLabel testLabel;
	private static JLabel storageLabel;
	private static JTextField dirTextField;

	public static void main(String[] args) {

		// setuping varibles based on OS
		if (IS_WINDOWS) {
			userdir = System.getProperty("user.home") + "/AppData/Roaming/";
		} else if (IS_MAC) {
			userdir = System.getProperty("user.home") + "/Library/Application Support/";
		} else if (IS_UNIX) {
			userdir = System.getProperty("user.home") + "/";
		}

		// initializing variables
		minecraft_directory = userdir + ".minecraft";
		minecraft_instance_manager_directory = userdir + ".minecraft-instance-manager-java/";
		instances_directory = null;
		config_file_string = minecraft_instance_manager_directory + "config.ini";
		config_file = new File(config_file_string);

		File minecraft_instance_manager_directory_file = new File(minecraft_instance_manager_directory);
		File creatingIni = new File(minecraft_instance_manager_directory + "config.ini");
		Path minecraft_instance_manager_directory_path = Paths.get(minecraft_instance_manager_directory);

		// checking if minecraft instance manager directory exists, and if not creating
		// this
		if (!Files.exists(minecraft_instance_manager_directory_path)) {
			minecraft_instance_manager_directory_file.mkdir();
		}

		// setuping ini
		try {
			config = new Wini(config_file);
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			try {
				creatingIni.createNewFile();
				config = new Wini(config_file);
				config.put("dirs", "instances_directory", "none");
				config.store();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		frame = new JFrame();
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setLayout(null);

		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Minecraft Instances Manager");

		activeInstance_label = new JLabel("No active instances");
		activeInstance_label.setBounds(10, 5, 200, 30);
		activeInstance_label.setFont(defaultfont);
		activeInstance_label.setForeground(Color.BLACK);
		panel.add(activeInstance_label);

		version = new JLabel("version: beta 0.4");
		version.setFont(defaultSmallfont);
		version.setBounds(1, 300, 100, 30);
		panel.add(version);

		testLabel = new JLabel(" ");
		testLabel.setBounds(1, 285, 310, 30);
		testLabel.setFont(defaultfont);
		testLabel.setForeground(Color.BLACK);
		panel.add(testLabel);

		storageLabel = new JLabel("Storage location:");
		storageLabel.setBounds(200, 225, 150, 30);
		storageLabel.setFont(defaultfont);
		storageLabel.setForeground(Color.BLACK);
		panel.add(storageLabel);

		String[] names = { "example1", "example2", "example3", "example4", "example5", "example6" };
		JList<String> instancesList = new JList<String>(names);
		instancesList.setBounds(10, 30, 150, 250);
		instancesList.setForeground(Color.BLACK);
		// instancesList.setBackground(Color.LIGHT_GRAY);
		instancesList.setFont(defaultBoldfont);
		instancesList.setVisibleRowCount(10);
		instancesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		instancesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
			}
		});
		panel.add(instancesList);

		// First column
		JButton activate_instance = new JButton("Activate");
		activate_instance.setBounds(200, 30, 150, 30);
		activate_instance.setForeground(Color.BLACK);
		activate_instance.setBackground(Color.WHITE);
		activate_instance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}

		});
		panel.add(activate_instance);

		JButton create_instance = new JButton("Create");
		create_instance.setBounds(200, 70, 150, 30);
		create_instance.setForeground(Color.BLACK);
		create_instance.setBackground(Color.WHITE);
		create_instance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}

		});
		panel.add(create_instance);

		JButton delete_instance = new JButton("Delete");
		delete_instance.setBounds(200, 110, 150, 30);
		delete_instance.setForeground(Color.BLACK);
		delete_instance.setBackground(Color.WHITE);
		delete_instance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		panel.add(delete_instance);

		// Second column
		JButton deactivate_instance = new JButton("Deactivate");
		deactivate_instance.setBounds(360, 30, 150, 30);
		deactivate_instance.setForeground(Color.BLACK);
		deactivate_instance.setBackground(Color.WHITE);
		deactivate_instance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}

		});
		panel.add(deactivate_instance);

		JButton rename_instance = new JButton("Rename");
		rename_instance.setBounds(360, 70, 150, 30);
		rename_instance.setForeground(Color.BLACK);
		rename_instance.setBackground(Color.WHITE);
		rename_instance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		panel.add(rename_instance);

		JButton duplicate_instance = new JButton("Duplicate");
		duplicate_instance.setBounds(360, 110, 150, 30);
		duplicate_instance.setForeground(Color.BLACK);
		duplicate_instance.setBackground(Color.WHITE);
		duplicate_instance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		panel.add(duplicate_instance);

		JButton reset_instance = new JButton("Reset");
		reset_instance.setBounds(200, 150, 310, 30);
		reset_instance.setForeground(Color.BLACK);
		reset_instance.setBackground(Color.WHITE);
		reset_instance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		panel.add(reset_instance);

		// Storage location part
		dirTextField = new JTextField(15);
		dirTextField.setBounds(200, 250, 310, 25);
		dirTextField.setToolTipText("Enter storage location");
		try {
			config = new Wini(config_file);
			dirTextField.setText(config.get("dirs", "instances_directory"));
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel.add(dirTextField);

		JButton setDir = new JButton("Ok");
		setDir.setBackground(Color.WHITE);
		setDir.setBounds(200, 285, 100, 25);
		setDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeStorage(dirTextField.getText());
			}
		});
		panel.add(setDir);

		JButton browseDir = new JButton("Browse");
		browseDir.setBackground(Color.WHITE);
		browseDir.setBounds(310, 285, 100, 25);
		browseDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.add(browseDir);

		JButton setDefaultDir = new JButton("Set default location");
		setDefaultDir.setToolTipText("Set default location");
		setDefaultDir.setBackground(Color.WHITE);
		setDefaultDir.setBounds(415, 285, 95, 25);
		setDefaultDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultStorage();
			}
		});
		panel.add(setDefaultDir);

		// setup gui
		frame.pack();
		frame.setResizable(false);
		frame.setSize(560, 360);
		frame.setLocation(660, 385);
		frame.setVisible(true);

	}

	public static void ActivateInstance(String name) {

	}

	public static void DeactivateInstance(String name) {

	}

	public static void DeleteInstance(String name) {

	}

	public static void RenameInstance(String oldname, String newname) {

	}

	public static void CreateInstance(String name) {

	}

	public static void DuplicateInstance(String name, String newname) {

	}

	public static void ResetInstance(String name) {

	}

	public static void changeStorage(String newDir) {
		try {
			config = new Wini(config_file);
			config.put("dirs", "instances_directory", newDir);
			config.store();
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setDefaultStorage() {
		Path instancePath = Paths.get(minecraft_instance_manager_directory + "instances/");
		File instanceFile = new File(instancePath.toString());
		try {
			if (!Files.exists(instancePath)) {
				instanceFile.mkdir();
			}
			config = new Wini(config_file);
			config.put("dirs", "instances_directory", instancePath.toString() + "\\");
			config.store();
			dirTextField.setText(instancePath.toString() + "\\");
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// an empty method so that the compiler does not swear
	public void actionPerformed(ActionEvent arg0) {

	}

}
