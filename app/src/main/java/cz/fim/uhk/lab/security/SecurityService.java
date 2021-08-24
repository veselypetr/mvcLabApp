/*
 * Vytvořeno jako studentský zápočtový projekt v rámci studia FIM UHK.
 * 2021 - Petr Veselý
 */

package cz.fim.uhk.lab.security;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}