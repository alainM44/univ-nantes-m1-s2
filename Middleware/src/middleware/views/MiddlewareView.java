package middleware.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import middleware.Activator;

/**
 * 
 * Class implémentant une view eclipse. Permet un chagemeent lazy des différents
 * pluggin du projet
 * 
 * @author Bizet Chaline Marguerite Rince
 * 
 */
public class MiddlewareView extends ViewPart
{
	/* Partie Aquisition */
	private String videoFile;
	private Button loadButton;
	private Button chooseFileButton;
	private Text videoTF;
	private Label invisibleLabel;

	/* Partie analyse */
	private Label label_choose_analyse;
	private Button choose_analyseButton;

	/* Partie Information */
	private Button postOnWebButton;
	private Text acces_token_t;
	private String acces_token;
	private Button configProxy;

	public MiddlewareView()
	{}

	/**
	 * IHM de la vue
	 */
	public void createPartControl(Composite parent)
	{
		/* Définition du Layout de la vue */
		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);
		final Shell shell = parent.getShell();
		/* Acquisition */
		chooseFileButton = new Button(parent, SWT.PUSH);
		chooseFileButton.setText("Choose video...");
		chooseFileButton
				.addSelectionListener(new ChooseFileButtonSelectionListener(
						shell));
		videoTF = new Text(parent, SWT.BORDER);
		videoTF.setText("Video source");
		loadButton = new Button(parent, SWT.NONE);
		loadButton.addSelectionListener(new LoadButtonSelectinoListener());
		loadButton.setText("Load video");
		loadButton.setFocus();
		// Label invisible pour permettre un bon positionnement dans le layout
		invisibleLabel = new Label(parent, SWT.NONE);
		invisibleLabel.setVisible(false);

		/* Analyse */
		choose_analyseButton = new Button(parent, SWT.NONE);
		choose_analyseButton.setEnabled(false);
		label_choose_analyse = new Label(parent, SWT.None);
		label_choose_analyse.setVisible(false);
		label_choose_analyse.setText("Analyse terminée");
		choose_analyseButton
				.addSelectionListener(new AnalyseButtonSelectinoListener());
		choose_analyseButton.setText("Lancer Analyse");

		/* Post online */
		postOnWebButton = new Button(parent, SWT.NONE);
		postOnWebButton.addSelectionListener(new PostButtonSelectinoListener());
		postOnWebButton.setEnabled(false);
		postOnWebButton.setText("Poster sur FB");
		acces_token = "";
		acces_token_t = new Text(parent, SWT.BORDER);
		acces_token_t.setEnabled(false);
		configProxy = new Button(parent, SWT.NONE);
		configProxy
				.addSelectionListener(new EnableProxyButtonSelectinoListener());
		configProxy.setEnabled(false);
		configProxy.setText("Enable proxy");

	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus()
	{}

	/**
	 * 
	 *@author Bizet Chaline Marguerite Rince Class Listener décrivant les
	 *         actions à effectuer lors de l'enclanchement du bouton de choix de
	 *         fichier vidéo.
	 */
	public class ChooseFileButtonSelectionListener implements SelectionListener
	{
		Shell shell;

		public ChooseFileButtonSelectionListener(Shell shell)
		{
			this.shell = shell;
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e)
		{
		// TODO Auto-generated method stub

		}

		@Override
		public void widgetSelected(SelectionEvent e)
		{
			FileDialog dlg = new FileDialog(shell, SWT.OPEN);
			dlg.setFilterExtensions(new String[] { "*.*" });
			String fn = dlg.open();
			if (fn != null)
			{
				videoTF.setText(fn);
			}
		}

	}

	/**
	 * 
	 *@author Bizet Chaline Marguerite Rince Class Listener décrivant les
	 *         actions à effectuer lors de l'enclanchement du bouton de
	 *         chargement.
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
			// isLoaded = true;
			loadButton.setEnabled(false);
			videoFile = videoTF.getText();
			videoTF.setEnabled(false);
			chooseFileButton.setEnabled(false);
			choose_analyseButton.setEnabled(true);
			choose_analyseButton.setFocus();
			Activator.getDefault().intialiserFlux(videoFile);

		}
	}

	/**
	 * 
	 *@author Bizet Chaline Marguerite Rince Class Listener décrivant les
	 *         actions à effectuer lors de l'enclanchement du bouton d'analyse.
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
			configProxy.setEnabled(true);
			acces_token_t.setEnabled(true);
			Activator.getDefault().intialiserReasoning();
			Activator.getDefault().sendFluxToReasoning();

		}

	}

	/**
	 * 
	 *@author Bizet Chaline Marguerite Rince Class Listener décrivant les
	 *         actions à effectuer lors de l'enclanchement du bouton de post
	 *         online.
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
	 *@author Bizet Chaline Marguerite Rince Class Listener décrivant les
	 *         actions à effectuer lors de l'enclanchement du bouton
	 *         d'activation du proxy.
	 */
	public class EnableProxyButtonSelectinoListener implements
			SelectionListener
	{

		@Override
		public void widgetDefaultSelected(SelectionEvent e)
		{
		// TODO Auto-generated method stub

		}

		@Override
		public void widgetSelected(SelectionEvent e)
		{
			configProxy.setEnabled(false);

			Activator.getDefault().setProxy();

		}

	}

	/**
	 * 
	 *@author Bizet Chaline Marguerite Rince Class Listener permettant d'efface
	 *         le contenu des champs texts à leur selection
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
