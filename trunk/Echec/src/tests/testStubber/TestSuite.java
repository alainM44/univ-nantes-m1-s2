package tests.testStubber;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value={
TestCouleur.class,
TestJoueur.class,
TestDame.class,
TestPion.class,
TestFou.class,
TestEchiquier.class,
TestCoup.class,
TestPartie.class,
TestCase.class
})

public class TestSuite {

}
