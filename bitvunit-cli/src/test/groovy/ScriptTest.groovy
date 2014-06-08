import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification

class ScriptTest extends Specification {

    static final String SCRIPT = 'bitvunit-cli/src/main/groovy/bitvunit.groovy'

    @Shared
    def script

    void setup() {
        script = new GroovyShell(new Binding()).parse(new File(SCRIPT))
    }

    @Ignore("until classpath issues when calling from console are resolved")
    void "should determine major and minor version information for next version number"() {
        expect:
        script.invokeMethod('getNextBitvUnitVersion', null) ==~ /\d+\.\d+/
    }

    @Ignore("until classpath issues when calling from console are resolved")
    void "should find rule categories to help choosing a category for newly created rules"() {
        expect:
        script.invokeMethod('getAvailableCategories', null).containsAll(['forms', 'links', 'media'])
    }

}
