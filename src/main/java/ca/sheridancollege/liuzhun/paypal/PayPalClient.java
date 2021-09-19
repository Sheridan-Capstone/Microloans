package ca.sheridancollege.liuzhun.paypal;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;


public class PayPalClient {

  /**
   *Set up the PayPal Java SDK environment with PayPal access credentials.  
   *This sample uses SandboxEnvironment. In production, use LiveEnvironment.
   */
  private PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
    "AU74ikxPWJiBuYeVxE-UVvVLuNdLMBFx7XSl1YChqshEe7mJMbvA9WKholLIBGpkYdtlM7sk9rvndhAM",
    "ECSMRXAeofI6K4-nX7YPWxGzPrv05Oc4hMKow--SKnVi3eZSmWuNnL_BBgM9X_2irsMI82M-JSqBDSYh");

  /**
   *PayPal HTTP client instance with environment that has access
   *credentials context. Use to invoke PayPal APIs.
   */
  PayPalHttpClient client = new PayPalHttpClient(environment);

  /**
   *Method to get client object
   *
   *@return PayPalHttpClient client
   */
  public PayPalHttpClient client() {
    return this.client;
  }
}
