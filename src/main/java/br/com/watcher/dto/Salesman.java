package br.com.watcher.dto;

import java.math.BigDecimal;

public class Salesman {

    private String code;
    private String document;
    private String name;
    private BigDecimal salary;

    public static final class Builder {
        private Salesman salesman;

        private Builder() {
            salesman = new Salesman();
        }

        public static Builder of() {
            return new Builder();
        }

        public Builder code(String code) {
            salesman.setCode(code);
            return this;
        }

        public Builder document(String document) {
            salesman.setDocument(document);
            return this;
        }

        public Builder name(String name) {
            salesman.setName(name);
            return this;
        }

        public Builder salary(String salary) {
            salesman.setSalary(new BigDecimal(salary.trim()));
            return this;
        }

        public Salesman build() {
            return salesman;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Salesman{" +
                "code='" + code + '\'' +
                ", document='" + document + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
