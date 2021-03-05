/*
 * Copyright (c) 2016, Innoave.com
 * All rights reserved.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL INNOAVE.COM OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package io.scalatestfx.api

import scala.jdk.CollectionConverters._

object ImplicitConversions extends ImplicitConversions

trait ImplicitConversions extends JfxConversions

object JfxConversions extends JfxConversions

trait JfxConversions {
  import javafx.{stage => jfxst}
  import javafx.{scene => jfxsc}
  import javafx.scene.{input => jfxin}
  import scalafx.stage.Window
  import scalafx.scene.Node
  import scalafx.scene.input.KeyCode
  import scalafx.scene.input.MouseButton
  import scalafx.scene.input.InputIncludes._
  import scalafx.stage.StageIncludes._
  import scalafx.scene.SceneIncludes._

  implicit def asSfxWindowList(windowList: java.util.List[jfxst.Window]): Seq[Window] =
    windowList.asScala.map(jfxWindow2sfx).toList

  implicit def asSfxNodeSet(nodeSet: java.util.Set[jfxsc.Node]): Set[Node] =
    nodeSet.asScala.map(jfxNode2sfx).toSet

  implicit def asSfxNodeList(nodeList: java.util.List[jfxsc.Node]): List[Node] =
    nodeList.asScala.map(jfxNode2sfx).toList

  implicit def asSfxMouseButtonSeq(mouseButtons: Seq[jfxin.MouseButton]): Seq[MouseButton] =
    mouseButtons.map { mouseButton => mouseButton }

  implicit def asJfxNodeSet(nodeSet: Set[Node]): Set[jfxsc.Node] =
    nodeSet.map { node => node }

  implicit def asJfxNodeSet(nodeList: List[Node]): List[jfxsc.Node] =
    nodeList.map { node => node }

  implicit def asJfxKeyCodeSeq(keyCodes: Seq[KeyCode]): Seq[jfxin.KeyCode] =
    keyCodes.map { keyCode => keyCode }

  implicit def asJfxMouseButtonSeq(mouseButtons: Seq[MouseButton]): Seq[jfxin.MouseButton] =
    mouseButtons.map { mouseButton => mouseButton }

}
