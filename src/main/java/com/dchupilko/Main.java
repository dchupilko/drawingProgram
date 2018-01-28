package com.dchupilko;

import com.dchupilko.constants.Constants;
import com.dchupilko.draw.InputParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.println(Constants.WELCOME_MSG);

        try (Scanner scanner = new Scanner(System.in)) {
            InputParser parser = new InputParser();
            parser.parseInput(scanner);
        } catch (Exception e) {
            LOGGER.error("Exception occurred", e);
            throw e;
        }
    }
}
