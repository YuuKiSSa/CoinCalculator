package sg.edu.nus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Path("/calculate-coins")
@Produces(MediaType.APPLICATION_JSON)
public class CoinCalculatorResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Double> calculateCoins(CoinRequest coinRequest) {
        try{
            double amount = coinRequest.getAmount();
            List<Double> denominations = coinRequest.getDenominations();

            validateInput(amount, denominations);

            List<Double> result = new ArrayList<>();
            denominations.sort(Collections.reverseOrder());

            for (double coin : denominations) {
                while (amount - coin > -0.001 ) {
                    result.add(coin);
                    amount -= coin;
                }
            }

            return result;
        }catch (IllegalArgumentException e){
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build());
        }

    }

    private void validateInput(double amount, List<Double> denominations) {
        if (amount < 0 || amount > 10000.0) {
            throw new IllegalArgumentException("Amount must be between 0 and 10,000.0");
        }
        if (denominations == null || denominations.isEmpty()) {
            throw new IllegalArgumentException("Denominations list must not be empty");
        }
        for (double denomination : denominations) {
            if (!isValidDenomination(denomination)) {
                throw new IllegalArgumentException("Invalid denomination: " + denomination);
            }
        }
    }

    private boolean isValidDenomination(double denomination) {
        List<Double> validDenominations = List.of(1000.0, 100.0, 50.0, 10.0, 5.0, 2.0, 1.0, 0.5, 0.2, 0.1, 0.05, 0.01);
        return validDenominations.contains(denomination);
    }
}
