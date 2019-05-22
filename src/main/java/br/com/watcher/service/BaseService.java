package br.com.watcher.service;

import br.com.watcher.dto.DataProcess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

abstract class BaseService {

    abstract void doWork(String line, DataProcess dataProcess);

    private final String DELIMITER = "ç";

    List<String> splitLine(String line, int delimiterQtd) {
        List<String> data = Arrays.asList(line.split(DELIMITER));
        if (data.size() == delimiterQtd)
            return data;

        return this.customSplitLine(data, delimiterQtd);
    }

    private List<String> customSplitLine(List<String> data, int delimiterQtd) {
        List<String> newData = new ArrayList<>();

        data.forEach(splitValue -> {
            if (onlyNumbers(splitValue) || upperFirstLetter(splitValue) || arrayValue(splitValue))
                newData.add(splitValue);
            else {
                int pos = newData.size() - 1;
                newData.set(pos, newData.get(pos).concat(DELIMITER).concat(splitValue));
            }
        });

        return (newData.size() == delimiterQtd) ? newData : Collections.EMPTY_LIST;
    }

    private boolean onlyNumbers(String value) {
        return value.trim().matches("([0-9-.]+)");
    }

    private boolean upperFirstLetter(String value) {
        return Character.isUpperCase(value.charAt(0));
    }

    private boolean arrayValue(String value) {
        return value.trim().matches("(\\[.*\\])");
    }
}
