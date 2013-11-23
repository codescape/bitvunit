import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import spock.lang.Shared
import spock.lang.Specification

@Ignore("test runs from the IDE but not during the Maven build because of a wrong path to the script")
class ScriptTest extends Specification {

    static final String SCRIPT = 'bitvunit-cli/src/main/groovy/bitvunit.groovy'

    @Shared
    def script

    @Before
    void setup() {
        script = new GroovyShell(new Binding()).parse(new File(SCRIPT))
    }

    @Test
    void "should determine major and minor version information for next version number"() {
        expect:
        script.invokeMethod('getNextBitvUnitVersion', null) ==~ /\d+\.\d+/
    }

    @Test
    void "should find rule categories to help choosing a category for newly created rules"() {
        expect:
        script.invokeMethod('getAvailableCategories', null).containsAll(['forms','links', 'media'])
    }

}
