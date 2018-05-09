package br.com.cardiobeat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import com.fazecast.jSerialComm.SerialPort;


public class Tela {
	
	//Cria as variáveis estáticas
	static SerialPort chosenPort;
	static int x = 0;
	boolean run = false;
	static int language = 1;
	static String chTitle = "BATIMENTOS CARDÍACOS";
	static String genM = "MASCULINO";
	static String genF = "FEMININO";
	static String port = "PORTA";
	static String aplicar = "APLICAR";
	static double sw = 0;
	static double sh = 0;
	static int w = 0;
	static int h = 0;
	
	
	public static void main(String [] args) {
					

	    /*Captura a medida da tela
		//Toolkit tk = Toolkit.getDefaultToolkit();
	    //Dimension d = tk.getScreenSize();
	    //sw = d.width;
	    //sh = d.height;
	    
		//Calcula o tamanho da janela proporcionalmente a tela
		//double pw = sw*.6; 
		//double ph = sh*.6;
	    //w = (int) pw;
	    h = (int) ph;
		
	    System.out.println("Screen width = " + sw);
	    System.out.println("Screen height = " + sh);
	    System.out.println("Screen width = " + w);
	    System.out.println("Screen height = " + h);

		*/
		
		//Cria as cores a serem usadas no layout
		Color fundo = new Color(42,45,55);
		Color amarelo = new Color(0xffcc29);
		Color dark = new Color(104,119,142);
		Color pop_up = new Color(22,26,35);
		Color data = new Color(31,214,209);
		
		//Cria as fontes que serão utilizadas
		Font padrao = new Font("Century Gothic", Font.PLAIN, 18);
		Font dados_big = new Font("Century Gothic", Font.BOLD, 48);

		
		
		// Cria os botões que serão inseridos na tela
		
		//Declaração das imagens dos botões
		ImageIcon imgStartStop = new ImageIcon("img/start_stop_bt2.png");
		ImageIcon imgMin = new ImageIcon("img/minime_bt.png");
		ImageIcon imgMax = new ImageIcon("img/maxime_bt.png");
		ImageIcon imgClose = new ImageIcon("img/close_bt.png");
		ImageIcon imgTitle = new ImageIcon("img/cardiobeat_icon.png");
		ImageIcon imgStartStop_off = new ImageIcon("img/start_stop_bt2_off.png");
		ImageIcon setupIcon = new ImageIcon("img/config_bt.png");
		ImageIcon setupIcon_off = new ImageIcon("img/config_bt_off.png");
		ImageIcon imgUser = new ImageIcon("img/person_icon.png");
		ImageIcon imgUser_off = new ImageIcon("img/person_icon_off.png");
		ImageIcon imgPort = new ImageIcon("img/com_bt.png");
		ImageIcon imgPort_off = new ImageIcon("img/com_bt_off.png");
		
		
		//Botão Minimizar
		JButton bt_min = new JButton();
		bt_min.setIcon(imgMin);
		bt_min.setLayout(null);
		bt_min.setBounds(1038, 15, 20, 20);
		bt_min.setBorder(null);
		bt_min.setBackground(null);
		
		
		//Botão Maximizar
		JButton bt_max = new JButton();
		bt_max.setIcon(imgMax);
		bt_max.setLayout(null);
		bt_max.setBounds(1015, 15, 20, 20);
		bt_max.setBorder(null);
		bt_max.setBackground(null);
		
		
		//Botão Fechar
		JButton bt_close = new JButton();
		bt_close.setIcon(imgClose);
		bt_close.setLayout(null);
		bt_close.setBounds(1060, 15, 20, 20);
		bt_close.setBorder(null);
		bt_close.setBackground(null);
		
		
		//Botão Iniciar/Parar
		JButton bt_start = new JButton();
		bt_start.setIcon(imgStartStop_off);
		bt_start.setLayout(null);
		bt_start.setBounds(840, 440, 106, 106);
		bt_start.setBorder(null);
		bt_start.setBackground(null);
		
		//Botão Setup
		JButton bt_setup = new JButton();
		bt_setup.setBounds(670, 440, 106, 106);
		bt_setup.setIcon(setupIcon_off);
		bt_setup.setLayout(null);
		bt_setup.setBorder(null);
		bt_setup.setBackground(null);
		
		//Botão Dados do Usuário
		JButton bt_user = new JButton();
		bt_user.setBounds(480, 440, 126, 106);
		bt_user.setIcon(imgUser_off);
		bt_user.setLayout(null);
		bt_user.setBorder(null);
		bt_user.setBackground(null);
		
		//Botão Porta de Comunicação
		JButton bt_com = new JButton();
		bt_com.setBounds(290, 440, 126, 106);
		bt_com.setIcon(imgPort_off);
		bt_com.setLayout(null);
		bt_com.setBorder(null);
		bt_com.setBackground(null);
		
		
		
		//---------- CRIA A TELA PRINCIPAL DO PROGRAMA -------------//
		JFrame principal = new JFrame();
		
		

	    
		
		principal.setLayout(null);
		principal.setSize(1152,648);
		//principal.setSize(w,h);
		principal.setLocationRelativeTo(null);
		principal.setTitle("CardioBeat");
		principal.setUndecorated(true);
		
		principal.getContentPane().setBackground(fundo);
		principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		principal.setState (Frame.NORMAL);
		principal.setIconImage(imgTitle.getImage());
		
		
		
		
		//---------- CRIA A TELA DE INFORMAÇÕES SOBRE -------------//
		JFrame sobre  = new JFrame();
		sobre.setLayout(null);
		sobre.setSize(340, 240);
		sobre.setVisible(false);
		sobre.setUndecorated(false);
		sobre.setState (Frame.NORMAL);
		sobre.setLocationRelativeTo(null);
		sobre.setBackground(amarelo);
		sobre.setUndecorated(true);
		
		
		//--------- CRIA A TELA DE SETUP ------------------//
		JFrame setup = new JFrame();
		setup.setLayout(null);
		setup.setSize(370,200);
		setup.setLocationRelativeTo(null);
		setup.setTitle("CONFIGURAÇÕES");
		setup.setUndecorated(true);
		setup.setVisible(false);
		setup.getContentPane().setBackground(pop_up);
		setup.setState (Frame.NORMAL);
		
	
		
		//---------- CRIA OS LABELS -------------//
		
		//Label IMC
		JLabel imc_lbl = new JLabel("IMC");
		imc_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		imc_lbl.setBounds(0,25, 190,20);
		imc_lbl.setBackground(null);
		imc_lbl.setFont(padrao);
		imc_lbl.setForeground(dark);
		
		//Label IMC Resultado
		JLabel imc_txt_lbl = new JLabel("XXXXX");
		imc_txt_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		imc_txt_lbl.setBounds(0,55,190,40);
		imc_txt_lbl.setBackground(null);
		imc_txt_lbl.setFont(dados_big);
		imc_txt_lbl.setForeground(data);

		//Label IMC Obeso/Normal
		JLabel imc_lbl_info = new JLabel("xxxxxxxxxxx");
		imc_lbl_info.setHorizontalAlignment(SwingConstants.CENTER);
		imc_lbl_info.setBounds(0,105, 190,20);
		imc_lbl_info.setBackground(null);
		imc_lbl_info.setFont(padrao);
		imc_lbl_info.setForeground(data);
		
		//Label Frequência
		JLabel freq_lbl = new JLabel("FREQUÊNCIA (BPM)");
		freq_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		freq_lbl.setBounds(0,175, 190,20);
		freq_lbl.setBackground(null);
		freq_lbl.setFont(padrao);
		freq_lbl.setForeground(dark);
		
		//Label Frequência Resultado
		JLabel freq_txt_lbl = new JLabel("XXXXX");
		freq_txt_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		freq_txt_lbl.setBounds(0,205,190,40);
		freq_txt_lbl.setBackground(null);
		freq_txt_lbl.setFont(dados_big);
		freq_txt_lbl.setForeground(data);
		
		//Label Condição				
		JLabel cond_lbl = new JLabel("CONDIÇÃO");
		cond_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		cond_lbl.setBounds(0,295, 190,20);
		cond_lbl.setBackground(null);
		cond_lbl.setFont(padrao);
		cond_lbl.setForeground(dark);
		
		//Label Condição Resultado
		JLabel cond_txt_lbl = new JLabel("XXXXX");
		cond_txt_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		cond_txt_lbl.setBounds(0,325,190,40);
		cond_txt_lbl.setBackground(null);
		cond_txt_lbl.setFont(dados_big);
		cond_txt_lbl.setForeground(data);
		
				
		//Define o label da logo
		JLabel logo = new JLabel(new ImageIcon("img/cardiobeat_logo.png"));
		
		//double c = sw * 0.18;
		//double k = sh * 0.77;
		
		//int lw = (int) c;
		//int lh = (int) k;
		
		logo.setBounds(0, 15, 190, 100);
		
		//Boneco de gênero
		JLabel body = new JLabel(new ImageIcon("img/body_icon.png"));
		body.setBounds(10, 50, 39, 79);

		//Label Dados do Paciente
		JLabel data_lbl = new JLabel("DADOS DO PACIENTE");
		data_lbl.setBounds(10,0,280,30);
		data_lbl.setFont(padrao);
		data_lbl.setForeground(dark);
				
		//Label Gênero
		JLabel genero_lbl = new JLabel("GÊNERO");
		genero_lbl.setBounds(60,30,280,30);
		genero_lbl.setFont(padrao);
		genero_lbl.setForeground(amarelo);
		
		//Caixa de seleção do gênero
		JComboBox<String> genList = new JComboBox<String>();
		genList.setBounds(160, 35, 140, 25);
		genList.setFont(padrao);
		genList.setBackground(dark);
		
		
		genList.addItem(genM);
		genList.addItem(genF);
		
		
		
		
		
		//Label Idade
		JLabel idade_lbl = new JLabel("IDADE");
		idade_lbl.setBounds(60,60,280,30);
		idade_lbl.setFont(padrao);
		idade_lbl.setForeground(amarelo);
		
		//Label Anos
		JLabel anos_lbl = new JLabel("ANOS");
		anos_lbl.setBounds(190,60,280,30);
		anos_lbl.setFont(padrao);
		anos_lbl.setForeground(amarelo);
		

		//Label Peso
		JLabel peso_lbl = new JLabel("PESO");
		peso_lbl.setBounds(60,90,280,30);
		peso_lbl.setFont(padrao);
		peso_lbl.setForeground(amarelo);
		
		//Label Quilos
		JLabel kg_lbl = new JLabel("KG");
		kg_lbl.setBounds(190,90,280,30);
		kg_lbl.setFont(padrao);
		kg_lbl.setForeground(amarelo);
		
		//Label Altura
		JLabel altura_lbl = new JLabel("ALTURA");
		altura_lbl.setBounds(60,120,280,30);
		altura_lbl.setFont(padrao);
		altura_lbl.setForeground(amarelo);
		
		//Label Centimetros
		JLabel cm_lbl = new JLabel("CM");
		cm_lbl.setBounds(190,120,280,30);
		cm_lbl.setFont(padrao);
		cm_lbl.setForeground(amarelo);
		
		
		//------- CAIXAS DE TEXTO -----------------

		
		JTextField status_txt = new JTextField("Status");
		status_txt.setBounds(200, 15, 350, 20);
		status_txt.setLayout(null);
		status_txt.setForeground(dark);
		status_txt.setBackground(null);
		status_txt.setBorder(null);
		status_txt.setFont(padrao);
		

		JTextField idade_txt = new JTextField(20);
		idade_txt.setBounds(155, 65, 30, 20);
		idade_txt.setLayout(null);
		idade_txt.setBackground(null);
		idade_txt.setBorder(null);
		idade_txt.setForeground(Color.WHITE);
		idade_txt.setFont(padrao);
		idade_txt.setText("0");
		idade_txt.setHorizontalAlignment(SwingConstants.RIGHT);
		
		
		JTextField peso_txt = new JTextField(20);
		peso_txt.setBounds(155, 95, 30, 20);
		peso_txt.setLayout(null);
		peso_txt.setBackground(null);
		peso_txt.setBorder(null);
		peso_txt.setForeground(Color.WHITE);
		peso_txt.setFont(padrao);
		peso_txt.setText("0");
		peso_txt.setHorizontalAlignment(SwingConstants.RIGHT);
		
		
		JTextField altura_txt = new JTextField(20);
		altura_txt.setBounds(155, 125, 30, 20);
		altura_txt.setLayout(null);
		altura_txt.setBackground(null);
		altura_txt.setBorder(null);
		altura_txt.setForeground(Color.WHITE);
		altura_txt.setFont(padrao);
		altura_txt.setText("0");
		altura_txt.setHorizontalAlignment(SwingConstants.RIGHT);
		

		
		//--------- PAINEIS --------------
		
		//Cria o painel com os dados do paciente
		JPanel pac_data = new JPanel();
		pac_data.setLayout(null);
		pac_data.setBounds(192,300,488,180);
		pac_data.setBackground(null);
		
		
		//Cria o painel da logo
		JPanel logoprg = new JPanel();
		logoprg.setLayout(null);
		logoprg.setBounds(0,0,190,200);
		logoprg.setBackground(Color.DARK_GRAY);
		
							
		//Cria o painel de infos
		JPanel infos = new JPanel();
		
			
		//double a = w * 0.18;
		//double b = h * 0.77;
		
		//int iw = (int) a;
		//int ih = (int) b;
		
		infos.setLayout(null);
		infos.setBackground(Color.DARK_GRAY);
		infos.setBounds(0, 150, 190, 498);
		
		

		//Cria o chart
		XYSeries series = new XYSeries("Sinal ECG"); //Cria a série do chart
		XYSeriesCollection dataset = new XYSeriesCollection(series); //Convertendo a series do chart em dataset
		JFreeChart chart = ChartFactory.createXYLineChart(chTitle, "", "", dataset); //Parametros de construção do chart
		chart.getXYPlot().setRangeAxis(null);
		chart.getXYPlot().setDomainAxis(null);

		
		
		//Cria o painel para conter o gráfico
		ChartPanel BPM_Panel = new ChartPanel(chart);
		BPM_Panel.setBackground(amarelo);
		BPM_Panel.setPreferredSize(new Dimension (890,300));
		BPM_Panel.setBounds(200, 85, 890, 300);
		//BPM_Panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		//Configurações de cores e fontes do chart
        chart.setBackgroundPaint(new java.awt.Color(42, 46, 55));
        chart.getTitle().setPaint(amarelo);
        chart.getTitle().setFont(padrao);
        chart.getXYPlot().setBackgroundPaint(new java.awt.Color(42, 46, 55));
        chart.getXYPlot().setOutlinePaint(new java.awt.Color(255, 204, 41));
        chart.getXYPlot().setDomainGridlinePaint(new java.awt.Color(42, 46, 55));
        chart.getXYPlot().setRangeGridlinePaint(new java.awt.Color(42, 46, 55));
        chart.getLegend().setVisible(false);
        chart.getLegend().setItemFont(new java.awt.Font("Century Gothic", 0, 12));
        chart.getLegend().setItemPaint(new java.awt.Color(255, 204, 41));
        chart.getLegend().setBackgroundPaint(new java.awt.Color(42, 46, 55));
		

		
		//Adiciona todos os elementos gráficos na tela principal
		principal.add(bt_start);
		principal.add(bt_min);
		principal.add(bt_max);
		principal.add(bt_close);
		principal.add(infos);
		principal.add(logoprg);
		//principal.add(pac_data);
		principal.add(bt_setup);
		principal.add(status_txt);
		principal.add(BPM_Panel);
		principal.add(bt_user);
		principal.add(bt_com);
		

		
		//infos.add(logo);
		infos.add(imc_lbl);
		infos.add(imc_txt_lbl);
		infos.add(imc_lbl_info);
		infos.add(freq_lbl);
		infos.add(freq_txt_lbl);
		infos.add(cond_lbl);
		infos.add(cond_txt_lbl);
		
		logoprg.add(logo);
		
		//Carrega os elementos de dados do paciente
		pac_data.add(idade_lbl);
		pac_data.add(peso_lbl);
		pac_data.add(altura_lbl);
		pac_data.add(data_lbl);
		pac_data.add(idade_txt);
		pac_data.add(peso_txt);
		pac_data.add(altura_txt);
		pac_data.add(genero_lbl);
		pac_data.add(body);
		pac_data.add(anos_lbl);
		pac_data.add(kg_lbl);
		pac_data.add(cm_lbl);
		pac_data.add(genList);
		
		
		principal.setVisible(true);
		principal.repaint();
		
		
		//--------- AÇÕES DOS BOTÕES E CAIXAS DE TEXTO ------------------//
		
		//Botão Porta de Comunicação
		bt_com.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				
								
				//principal.setEnabled(false);
				
				JLabel title_lbl = new JLabel("PORTA DE COMUNICAÇÃO");
				JComboBox<String> portList = new JComboBox<String>();
				JComboBox<String> baudList = new JComboBox<String>();
				JComboBox<String> langList = new JComboBox<String>();
				JLabel port_lbl = new JLabel(port);
				JLabel baud_lbl = new JLabel("BAUD RATE:");
				JLabel lang_lbl = new JLabel("IDIOMA:");
				JPanel title_bg = new JPanel();
				
				title_bg.setLayout(null);
				title_bg.setBackground(dark);
				title_bg.setBounds(5, 5, 470, 30);
				title_bg.add(title_lbl);
				
				portList.setBounds(30, 75, 140, 25);
				portList.setFont(padrao);
				portList.setBackground(dark);
				
				
				langList.setBounds(190, 75, 140, 25);
				langList.setBackground(dark);
				langList.setFont(padrao);
				
				langList.addItem("PORTUGUÊS");
				langList.addItem("ENGLISH");
				
						
				
				//Populando a lista de portas
				portList.addItem("");
				SerialPort[] portNames = SerialPort.getCommPorts();
				for( int pt = 0; pt < portNames.length; pt++)
					portList.addItem(portNames[pt].getSystemPortName());
				
				baudList.setBounds(200, 75, 140, 25);
				baudList.setFont(padrao);
				baudList.setBackground(dark);
				
				String baudRates[] = {"300","900","1200","2400","4800","9600","19200","38400","57600","115200","230400"};
				
				//Popula a lista de baudrates				
				for (int i=0; i<11; i++)
				{
					baudList.addItem(baudRates[i]);
				}

								
				//Botão Aplicar da janela de configuração
				JButton setup_close_bt= new JButton(aplicar);
				setup_close_bt.setBounds(120, 140, 120, 30);
				setup_close_bt.setFont(padrao);
				setup_close_bt.setHorizontalAlignment(setup_close_bt.CENTER);

				
				//Ação do botão Aplicar da janela de configuraçãop
				setup_close_bt.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
							chosenPort = SerialPort.getCommPort(portList.getSelectedItem().toString());
							chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
							System.out.println(portList.getSelectedItem().toString());
							setup.dispose();
							
							
							if (langList.getSelectedItem() == "PORTUGUÊS") {
								//Muda o idioma da interface para português
								System.out.println("Idioma português");
								imc_lbl.setText("IMC");
								freq_lbl.setText("FREQUÊNCIA (BPM)");
								cond_lbl.setText("CONDIÇÃO");
								data_lbl.setText("DADOS DO PACIENTE");
								genero_lbl.setText("GÊNERO");
								idade_lbl.setText("IDADE");
								peso_lbl.setText("PESO");
								altura_lbl.setText("ALTURA");
								anos_lbl.setText("ANOS");
								chTitle = "BATIMENTOS CARDÍACOS";
								BPM_Panel.repaint();
								port = "PORTA";
								port_lbl.setText(port);
								aplicar = "APLICAR";
								setup_close_bt.setText(aplicar);
								
								genList.removeAllItems();
								
								genM = "MASCULINO";
								genF = "FEMININO";
								
								genList.addItem(genM);
								genList.addItem(genF);
								
								
								
								
							}
							else {
								//Muda o idioma da interface para inglês
								System.out.println("Idioma inglês");
								imc_lbl.setText("BMI");
								freq_lbl.setText("FREQUENCY (BPM)");
								cond_lbl.setText("CONDITION");
								data_lbl.setText("PATIENT DATA");
								genero_lbl.setText("GENRE");
								idade_lbl.setText("AGE");
								peso_lbl.setText("WEIGHT");
								altura_lbl.setText("HEIGHT");
								anos_lbl.setText("YEARS");
								chTitle = "HEART BEATS";
								BPM_Panel.repaint();
								port = "PORT";
								port_lbl.setText(port);
								aplicar = "APPLY";
								setup_close_bt.setText(aplicar);
								
								
								genList.removeAllItems();								
								
								genM = "MALE";
								genF = "FEMALE";
								
								genList.addItem(genM);
								genList.addItem(genF);
								
							}
								
							
						} 
					});
				
				
				
				port_lbl.setBounds(30, 50, 80, 20);
				port_lbl.setFont(padrao);
				port_lbl.setForeground(dark);
				
				lang_lbl.setBounds(190, 50, 90, 20);
				lang_lbl.setFont(padrao);
				lang_lbl.setForeground(dark);
				
				
				baud_lbl.setBounds(200, 50, 120, 20);
				baud_lbl.setFont(padrao);
				baud_lbl.setForeground(dark);
				
				title_lbl.setBounds(10,0,280,25);
				title_lbl.setFont(padrao);
				title_lbl.setForeground(amarelo);
				
				
				//Adiciona os elementos a caixa de diálogo Setup
				setup.add(title_bg);
				setup.add(port_lbl);
				//setup.add(title_lbl);
				setup.add(pac_data);
				setup.add(portList);
				setup.add(baudList);
				setup.add(baud_lbl);
				setup.add(setup_close_bt);
				//setup.add(lang_lbl);
				//setup.add(langList);
				
				setup.setVisible(true);
				setup.repaint();
			}
			
		});
		
				
		idade_txt.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				//idade_txt.setText(null);
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {
				if (idade_txt.getText() == null)
				{
					System.out.println("Entered");
					idade_txt.setText("0");
				}
				
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {	}
		});
		
		
		// Efeito no botão Iniciar
		bt_start.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {
            	//System.out.println("Entered");
            	bt_start.setIcon(imgStartStop);
            	//sobre.setVisible(true);
            	
            }
            public void mouseExited(MouseEvent e) {
            	//System.out.println("Exited");
            	bt_start.setIcon(imgStartStop_off);
            	//sobre.setVisible(false);
            }
        });
		
		
		// Efeito no botão Setup
		bt_setup.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {
            	//System.out.println("Entered");
            	bt_setup.setIcon(setupIcon);
            	//sobre.setVisible(true);
            	
            }
            public void mouseExited(MouseEvent e) {
            	//System.out.println("Exited");
            	bt_setup.setIcon(setupIcon_off);
            	//sobre.setVisible(false);
            }
        });
		
		
		// Efeito no botão Usuário
		bt_user.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {
            	//System.out.println("Entered");
            	bt_user.setIcon(imgUser);
            	//sobre.setVisible(true);
            	
            }
            public void mouseExited(MouseEvent e) {
            	//System.out.println("Exited");
            	bt_user.setIcon(imgUser_off);
            	//sobre.setVisible(false);
            }
        });
		
		
		// Efeito no botão Porta
		bt_com.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {
            	//System.out.println("Entered");
            	bt_com.setIcon(imgPort);
            	//sobre.setVisible(true);
            	
            }
            public void mouseExited(MouseEvent e) {
            	//System.out.println("Exited");
            	bt_com.setIcon(imgPort_off);
            	//sobre.setVisible(false);
            }
        });
		
		
				
		//Botão fechar
		bt_close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int resposta;
				resposta = JOptionPane.showConfirmDialog(null, "Deseja sair?","Atenção",JOptionPane.YES_NO_OPTION);				
				if (resposta == JOptionPane.YES_OPTION) {
					x = 0;
					System.exit(0);
				} 
			}
		});
		
		
		
		//Botão Minimizar
		bt_min.addActionListener(new ActionListener() {
            @SuppressWarnings("static-access")
			@Override
            public void actionPerformed(ActionEvent e) {
                principal.setState(principal.ICONIFIED);
            }

		});

		
		//Botão Maximizar
		bt_max.addActionListener(new ActionListener() {
            @SuppressWarnings("static-access")
			@Override
            public void actionPerformed(ActionEvent e) {
            	//FlowLayout layout = new FlowLayout();
            	//infos.setLayout(layout);
            	
            	/*
            	double a1 = sw * 0.18;
            	double a2 = sh * 0.72;
            	
            	int wp = (int) sw;
            	int hp = (int) sh;
            	
            	principal.setSize(wp,hp);
            	principal.setLocationRelativeTo(null);
            	
            	int iw = (int) a1;
            	int ih = (int) a2;
            	
            	infos.setBounds(0, 190, iw, ih);
            	*/
            	
            	principal.setExtendedState(JFrame.MAXIMIZED_BOTH);
            	infos.repaint();
            	principal.repaint();
            	
            }

		});
		
		
		//Botão iniciar (ações)
		bt_start.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					
					// Loop que ficará scaneando a porta serial e recebendo os dados
					Thread System_Run = new Thread() {
						@Override public void run() {
							
							Scanner scanner =  new Scanner(chosenPort.getInputStream());
								while(scanner.hasNextLine()) {
									try {
										String line = scanner.nextLine();
										int number = Integer.parseInt(line);
										series.add(x++, 1023 - number);
										BPM_Panel.repaint();
									} catch(NullPointerException exception) {
										//JOptionPane.showMessageDialog(null, "Sem conexão", "Erro1",JOptionPane.YES_NO_OPTION);
										System.out.println("Deu erro!");
									}
								}
								scanner.close();
						}
						
					};
					
						//Inicia o loop
						System_Run.start();
						System.out.println("Estou rodando");
						status_txt.setText("CONECTADO...");
						System.out.println(chosenPort);

				}
				
		});
	
	}
	
}