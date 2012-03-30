package middleware.views;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import middleware.Activator;
/**
 *
 * Class implémentant une view eclipse.
 * Permet un chagemeent lazy des différents pluggin du projet
 * @author Bizet Chaline Marguerite Rince 
 * 
 */
public class MiddlewareView extends ViewPart
{
	/* Partie Aquisition */
	private String videoFile;
	private Button loadButton;
	private Text videoTF;

	/* Partie analyse */
	private Label label_choose_analyse;
	private Button choose_analyseButton;

	/* Partie Information */
	private Button postOnWebButton;
	private Text acces_token_t;
	private String acces_token;

	public MiddlewareView()
	{
	}

	/**
	 * IHM de la vue
	 */
	public void createPartControl(Composite parent)
	{
		/* Définition du Layout de la vue */
		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);
		/* Acquisition */
		loadButton = new Button(parent, SWT.NONE);
		loadButton.addSelectionListener(new LoadButtonSelectinoListener());
		loadButton.setText("Load video");
		loadButton.setFocus();
		videoTF = new Text(parent, SWT.NONE);
		videoTF.setText("Enter video_file");

		/* Analyse */
		choose_analyseButton = new Button(parent, SWT.NONE);
		choose_analyseButton.setEnabled(false);
		label_choose_analyse = new Label(parent, SWT.None);
		label_choose_analyse.setVisible(false);
		label_choose_analyse.setText("Analyse terminée");
		choose_analyseButton.addSelectionListener(new AnalyseButtonSelectinoListener());
		choose_analyseButton.setText("Lancer Analyse");

		/* Post online */
		postOnWebButton = new Button(parent, SWT.NONE);
		postOnWebButton.addSelectionListener(new PostButtonSelectinoListener());
		postOnWebButton.setEnabled(false);
		postOnWebButton.setText("Poster sur FB");

		acces_token = "";
		acces_token_t = new Text(parent, SWT.None);
		acces_token_t.setEnabled(false);

	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus()
	{
	}

	/**
	 * 
	 *@author Bizet Chaline Marguerite Rince Class Listener décrivant les
	 *          actions à effectuer lors de l'enclanchement du bouton de
	 *          chargement.
	 */
	public class LoadButtonSelectinoListener implements SelectionListener
	{

		@Override
		public void widgetDefaultSelected(SelectionEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void widgetSelected(SelectionEvent e)
		{
			// TODO Chargement de acquisition
			//isLoaded = true;
			loadButton.setEnabled(false);
			videoFile = videoTF.getText();
			videoTF.setEnabled(false);
			choose_analyseButton.setEnabled(true);
			choose_analyseButton.setFocus();
			Activator.getDefault().intialiserFlux(videoFile);

		}
	}

	/**
	 * 
	 *@author Bizet Chaline Marguerite Rince Class Listener décrivant les
	 *          actions à effectuer lors de l'enclanchement du bouton d'analyse.
	 */
	public class AnalyseButtonSelectinoListener implements SelectionListener
	{

		@Override
		public void widgetDefaultSelected(SelectionEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void widgetSelected(SelectionEvent e)
		{
			// TODO Chargement de acquisition
			label_choose_analyse.setVisible(true);
			choose_analyseButton.setEnabled(false);
			postOnWebButton.setEnabled(true);
			postOnWebButton.setFocus();
			acces_token_t.setEnabled(true);
			Activator.getDefault().intialiserReasoning();
			Activator.getDefault().sendFluxToReasoning();

		}

	}

	/**
	 * 
	 *@author Bizet Chaline Marguerite Rince Class Listener décrivant les
	 *          actions à effectuer lors de l'enclanchement du bouton de post
	 *          online.
	 */
	public class PostButtonSelectinoListener implements SelectionListener
	{

		@Override
		public void widgetDefaultSelected(SelectionEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void widgetSelected(SelectionEvent e)
		{

			postOnWebButton.setEnabled(false);
			acces_token = acces_token_t.getText();
			acces_token_t.setEnabled(false);

			Activator.getDefault().postMessage(acces_token);

	
		}

	}

	/**
	 * 
	 *@author Bizet Chaline Marguerite Rince Class Listener permettant
	 *          d'efface le contenu des champs texts à leur selection
	 * 
	 */
	public class TextFieldFocusListener implements FocusListener
	{

		@Override
		public void focusGained(FocusEvent e)
		{
			Text cible = (Text) e.widget;
			cible.setText("");
		}

		@Override
		public void focusLost(FocusEvent e)
		{ // TODO Auto-generated method stub

		}

	}

}
