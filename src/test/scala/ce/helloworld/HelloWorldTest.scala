package ce.helloworld

import org.scalatest.{FunSpec, ShouldMatchers}

/**
  * @author caie 
  * @since
  */
class HelloWorldTest extends FunSpec with ShouldMatchers {

  describe("Hello World") {
    it("can add two numbers int") {
      HelloWorld.add(1, 2) shouldBe 3
    }
  }


}
