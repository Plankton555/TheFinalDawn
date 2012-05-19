package projectrts.view;

import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;

/**
 * A class that creates the GUI for the menu
 * 
 * @author Filip Brynfors
 * 
 */
// TODO Afton: PMD: A class which only has private constructors should be final
public class MenuGUICreator {

	private MenuGUICreator() {
	}

	/**
	 * Creates the Main layer for the menu
	 * 
	 * @return the LayerBuilder for the layer
	 */
	public static LayerBuilder createMainLayer() {
		LayerBuilder layer = new LayerBuilder("Layer_Menu") {
			{
				childLayoutCenter();
				panel(createMenuPanel());

			}
		};
		return layer;
	}

	private static PanelBuilder createMenuPanel() {
		PanelBuilder builder = new PanelBuilder("Panel_Menu") {
			{
				childLayoutVertical();
				valignCenter();
				width("150px");

				// Buttons
				control(new ButtonBuilder("Button_Start", "Start Game") {
					{
						// TODO Afton: PMD: The String literal "100%" appears 5
						// times in this file; the first occurrence is here
						width("100%");
						alignCenter();
						interactOnClick("buttonStartClicked()");
					}
				});
				control(new ButtonBuilder("Button_ChangeDifficulty") {
					{
						width("100%");
						alignCenter();
						interactOnClick("buttonChangeClicked()");
					}
				});

				panel(new PanelBuilder("Panel_Spacer") {
					{
						childLayoutCenter();
						height("10px");
					}
				});

				control(new ButtonBuilder("Button_Exit", "Exit Game") {
					{
						width("100%");
						alignCenter();
						interactOnClick("buttonExitClicked()");
					}
				});
			}
		};
		return builder;
	}

	/**
	 * Creates the layer for the popupmenu
	 * 
	 * @return the LayerBuilder for the layer
	 */
	public static LayerBuilder createDifficultyPopupLayer() {
		LayerBuilder layer = new LayerBuilder("Layer_DifficultyPopup") {
			{
				childLayoutCenter();
				panel(createDifficultyPopupPanel());
			}
		};
		return layer;
	}

	private static PanelBuilder createDifficultyPopupPanel() {
		PanelBuilder builder = new PanelBuilder("Panel_DifficultyPopup") {
			{
				childLayoutCenter();
				width("100%");
				height("100%");
				visible(false);

				panel(new PanelBuilder("PanelDifficultyCenter") {
					{
						childLayoutVertical();

						control(new ButtonBuilder("Button_Easy", "Easy") {
							{
								alignCenter();
								interactOnClick("buttonEasyClicked()");
							}
						});

						control(new ButtonBuilder("Button_Medium", "Medium") {
							{
								alignCenter();
								interactOnClick("buttonMediumClicked()");
							}
						});

						control(new ButtonBuilder("Button_Hard", "Hard") {
							{
								alignCenter();
								interactOnClick("buttonHardClicked()");
							}
						});

						control(new ButtonBuilder("Button_Nightmare",
								"Nightmare") {
							{
								alignCenter();
								interactOnClick("buttonNightmareClicked()");
							}
						});

						panel(new PanelBuilder("Panel_Spacer") {
							{
								childLayoutCenter();
								height("10px");
							}
						});

						control(new ButtonBuilder("Button_Cancel", "Cancel") {
							{
								alignCenter();
								interactOnClick("buttonCancelClicked()");
							}
						});
					}
				});
			}
		};

		return builder;
	}

}
