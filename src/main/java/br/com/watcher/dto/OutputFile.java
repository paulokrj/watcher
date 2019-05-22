package br.com.watcher.dto;

public class OutputFile {

    private int totalOfCustomer;
    private int totalOfSalesman;
    private String mostExpensiveSale;
    private String worstSalesman;

    public static final class Builder {
        private OutputFile outputFile;

        private Builder() {
            outputFile = new OutputFile();
        }

        public static Builder of() {
            return new Builder();
        }

        public Builder totalOfCustomer(int totalOfCustomer) {
            outputFile.setTotalOfCustomer(totalOfCustomer);
            return this;
        }

        public Builder totalOfSalesman(int totalOfSalesman) {
            outputFile.setTotalOfSalesman(totalOfSalesman);
            return this;
        }

        public Builder mostExpensiveSale(String mostExpensiveSale) {
            outputFile.setMostExpensiveSale(mostExpensiveSale);
            return this;
        }

        public Builder worstSalesman(String worstSalesman) {
            outputFile.setWorstSalesman(worstSalesman);
            return this;
        }

        public OutputFile build() {
            return outputFile;
        }
    }

    public int getTotalOfClient() {
        return totalOfCustomer;
    }

    public void setTotalOfCustomer(int totalOfCustomer) {
        this.totalOfCustomer = totalOfCustomer;
    }

    public int getTotalOfSalesman() {
        return totalOfSalesman;
    }

    public void setTotalOfSalesman(int totalOfSalesman) {
        this.totalOfSalesman = totalOfSalesman;
    }

    public String getMostExpensiveSale() {
        return mostExpensiveSale;
    }

    public void setMostExpensiveSale(String mostExpensiveSale) {
        this.mostExpensiveSale = mostExpensiveSale;
    }

    public String getWorstSalesman() {
        return worstSalesman;
    }

    public void setWorstSalesman(String worstSalesman) {
        this.worstSalesman = worstSalesman;
    }

    @Override
    public String toString() {
        return "Quantidade de clientes no arquivo de entrada: " + totalOfCustomer +
                "\r\nQuantidade de vendedor no arquivo de entrada:" + totalOfSalesman +
                "\r\nID da venda mais cara: " + mostExpensiveSale +
                "\r\nO pior vendedor: " + worstSalesman;
    }
}
