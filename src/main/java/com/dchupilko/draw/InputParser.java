package com.dchupilko.draw;

import com.dchupilko.constants.ActionType;
import com.dchupilko.constants.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputParser {
    private static final Logger LOGGER = LogManager.getLogger(InputParser.class);

    private CanvasHandler canvasHandler = getCanvasHandler();

    public void parseInput(Scanner scanner) {
        LOGGER.debug("parseInput: start");

        System.out.print(Constants.LINE_PREFIX);
        while (scanner.hasNextLine()) {
            LOGGER.debug("parseInput: new iteration");
            try {
                ActionType option = ActionType.getValueByChar(scanner.next().charAt(0));
                switch (option) {
                    case CREATE_CANVAS:
                        LOGGER.debug("parseInput: create canvas");
                        getCanvasHandler().createCanvas(scanner.nextInt(), scanner.nextInt());
                        System.out.println(getCanvasHandler().getCanvas().toString());
                        System.out.print(Constants.LINE_PREFIX);
                        break;
                    case DRAW_LINE:
                        LOGGER.debug("parseInput: draw line");
                        getCanvasHandler().drawLine(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
                        System.out.println(getCanvasHandler().getCanvas().toString());
                        System.out.print(Constants.LINE_PREFIX);
                        break;
                    case DRAW_RECTANGLE:
                        LOGGER.debug("parseInput: draw rectangle");
                        getCanvasHandler().drawRectangle(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
                        System.out.println(getCanvasHandler().getCanvas().toString());
                        System.out.print(Constants.LINE_PREFIX);
                        break;
                    case FILL_AREA:
                        LOGGER.debug("parseInput: fill area");
                        getCanvasHandler().fillArea(scanner.nextInt(), scanner.nextInt(), scanner.next().charAt(0));
                        System.out.println(getCanvasHandler().getCanvas().toString());
                        System.out.print(Constants.LINE_PREFIX);
                        break;
                    case HELP:
                        LOGGER.debug("parseInput: help");
                        System.out.println(Constants.README);
                        System.out.print(Constants.LINE_PREFIX);
                        break;
                    case QUIT:
                        LOGGER.debug("parseInput: quit");
                        System.exit(0);
                        break;
                    default:
                        LOGGER.error("parseInput: " + Constants.UNRECOGNIZED_INPUT);
                        throw new IllegalArgumentException(Constants.UNRECOGNIZED_INPUT);
                }
            } catch (InputMismatchException e) {
                LOGGER.error("parseInput: Arguments have invalid format", e);
                System.out.println("Arguments have invalid format");
                scanner.skip(".*");
            } catch (NoSuchElementException e) {
                LOGGER.error("parseInput: Incorrect number of arguments", e);
                System.out.println("Incorrect number of arguments");
                scanner.skip(".*");
            } catch (Exception e) {
                LOGGER.error("parseInput: Exception during parsing input", e);
                System.out.println(e.getMessage());
                scanner.skip(".*");
            }
        }
    }

    protected CanvasHandler getCanvasHandler() {
        if (canvasHandler == null) {
            canvasHandler = new CanvasHandler();
        }
        return canvasHandler;
    }
}
