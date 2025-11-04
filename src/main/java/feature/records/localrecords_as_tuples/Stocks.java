package feature.records.localrecords_as_tuples;

import java.util.List;

public class Stocks {

    public static int simulatePrice(String ticker) {
        return 200 + ticker.chars().sum();
    }
    public static void main(String[] args) {
        var tickers = List.of("GOOG", "MSFT", "AMZN", "ORCL", "INTC");
        printPricesOver500(tickers);
    }
//
//    public static void printPricesOver500(List<String> tickers) {
//        tickers.stream()
//                .map(ticker -> simulatePrice(ticker))
//                .filter(price -> price > 500)
//                .forEach(System.out::println);
//    }

    public static void printPricesOver500(List<String> tickers) {
        record StockInfo(String ticker, double price){

            public String toString(){
                return String.format("Ticker : %s   has price of %g", ticker,price);
            }
        }

        tickers.stream()
                .map(ticker -> new StockInfo(ticker,simulatePrice(ticker)))
                .filter(s->s.price()> 500)
                .forEach(System.out::println);
    }

}
