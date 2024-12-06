package org.youcode.hunterleague.service;

import org.youcode.hunterleague.web.VMs.AuthVMs.AuthenticationRequest;
import org.youcode.hunterleague.web.VMs.AuthVMs.AuthenticationResponse;
import org.youcode.hunterleague.web.VMs.AuthVMs.RegisterRequest;

public interface AuthService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
