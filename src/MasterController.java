
import Communications.CommunicationsController;
import Generators.LifeGenerator;
import Generators.WorldGenerator;
import controller.Controller;
import java.io.IOException;
import view.View;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author thomas
 */
public class MasterController {

    private Controller controller;
    private WorldGenerator worldGenerator;
    private LifeGenerator lifeGenerator;
    private CommunicationsController comController;

    public MasterController(String peerHost) throws IOException {
        controller = new Controller();
        worldGenerator = new WorldGenerator(controller.getModel(), controller);
        lifeGenerator = new LifeGenerator(controller.getModel(), controller);
        worldGenerator.generateInitialWorld();
        setupListeners();

        new Thread(lifeGenerator).start();

        comController = new CommunicationsController(10000, 10001, peerHost, 10001, 10000);
        comController.start();
    }

    private void setupListeners() {
        View view = controller.getView();
        view.getControlpanel().getautomatico().addActionListener(e -> {
            boolean auto = view.getControlpanel().getautomatico().isSelected();
            lifeGenerator.setActive(auto);
            System.out.println("Modo automático: " + (auto ? "ACTIVADO" : "DESACTIVADO"));
        });

        view.getControlpanel().getWorldButton().addActionListener(e -> {
            worldGenerator.generateRandomWorld();
        });
    }
}
