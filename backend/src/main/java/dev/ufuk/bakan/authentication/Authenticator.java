package dev.ufuk.bakan.authentication;

import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.http.HTTPException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import dev.ufuk.bakan.account.service.AccountService;

@Component
public class Authenticator {

    private Map<Long,String> ticketMap = new HashMap<>();

    public void setTicket(long customerNo, String ticket){
        ticketMap.put(customerNo, ticket);
    }

    public String getTicket(long customerNo){
        return ticketMap.get(customerNo);
    }

    public void authenticate(long customerNo, String ticket) throws HTTPException{
        if(!ticketMap.containsKey(customerNo) || !ticketMap.get(customerNo).equals(ticket)){
            throw new HTTPException(403);// Forbidden
        }
    }
}
