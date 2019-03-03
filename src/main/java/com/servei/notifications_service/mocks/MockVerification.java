package com.servei.notifications_service.mocks;

public class MockVerification implements TokenVerificator {

    public boolean verifyToken(String token){
        if (token.equals("AACbpkMFarNdMwz1qVPV0mWcnfjSt0zMcNcUogSMgr2lcZU2G7qjf7B-f1lmTkhRpfgXFBwxzd9ad3vRD1Oymgk#")) {
            return true;
        } else return false;
    }

}
