package com.getir.readingisgood.readingisgood.authenticationservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.readingisgood.readingisgood.authenticationservice.configuration.JwtTokenUtil;
import com.getir.readingisgood.readingisgood.authenticationservice.models.UserDTO;
import com.getir.readingisgood.readingisgood.authenticationservice.requests.JwtRequest;
import com.getir.readingisgood.readingisgood.authenticationservice.responses.BaseResponse;
import com.getir.readingisgood.readingisgood.authenticationservice.responses.JwtResponse;
import com.getir.readingisgood.readingisgood.authenticationservice.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CustomerController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception
    {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveUser(@RequestBody UserDTO user) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        if (userDetailsService.isUserAlreadyExists(user.getUsername())){
            return objectMapper.writeValueAsString(new BaseResponse("ERR-0001","User already exists. Please choose different username."));
        }
        userDetailsService.save(user);
        return objectMapper.writeValueAsString(new BaseResponse("SUCCESSFUL",""));
    }

    @RequestMapping(value = "/mybookorders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCustomerOrders()
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return userDetails.getUsername();
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
